package com.mars.robot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.robot.dtos.ScentDto;
import com.mars.robot.models.Limit;
import com.mars.robot.models.Robot;

@Service
public class MoveRobotService {
	
	@Autowired
	ScentService scentService;
	
	private List<ScentDto> scentList = new ArrayList<>();
	
	public String move(Limit limit, Robot robot, String uid) {
		String result = "";
		try {
			
			if(robot.getStartPointX() > limit.getxMax() || robot.getStartPointY() > limit.getyMax()) {
				throw new Exception("The robot's starting point is outside the grid!");
			}
			
			String instructions = robot.getInstructions();
			if(instructions.length() > 100) {
				throw new Exception("The number of instructions cannot exceed 100");
			}
			
			boolean isLost = false;
			for (char i: instructions.toLowerCase().toCharArray()) {
				
				if(i != 'f') {
					robot.getOrientation().newOrientation(i);
					if(!scentList.isEmpty()) {
						for(ScentDto dto: scentList) {
							
							if(robot.getEndPointX() == dto.getX() && robot.getEndPointY() == dto.getY() &&
									robot.getOrientation().getCurrentOrientation().toLowerCase() == dto.getOrientation().toLowerCase()) {
								isLost = true;
								
							}
						}
					}
				}
				else {
					
					if(!isLost) {
						
						int newPointX = robot.getEndPointX() + robot.getOrientation().getCurrentOrientationPosition()[0];
						int newPointY = robot.getEndPointY() + robot.getOrientation().getCurrentOrientationPosition()[1];

						if((newPointX < limit.getxMin() || newPointX > limit.getxMax()) || 
								(newPointY < limit.getyMin() || newPointY > limit.getyMax())) {
							
							result = robot.getEndPointX() +" "+ robot.getEndPointY()+ " "+ robot.getOrientation().getCurrentOrientation().toUpperCase();
							result += " LOST";
							ScentDto dto = createDto(robot.getEndPointX(), robot.getEndPointY(), robot.getOrientation().getCurrentOrientation(), uid);
							scentService.addScent(dto);
							scentList.add(dto);
							return result;
						}
						
						result = newPointX +" "+ newPointY+ " ";
						robot.setEndPointX(newPointX);
						robot.setEndPointY(newPointY);
						
					}

					isLost=false;
					
				}

			}
			
			result +=  robot.getOrientation().getCurrentOrientation().toUpperCase();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	private ScentDto createDto(int x, int y, String orientation, String groupId) {
		ScentDto dto = new ScentDto();
		dto.setGroupId(groupId);
		dto.setOrientation(orientation);
		dto.setX(x);
		dto.setY(y);
		return dto;

	}
}
