package com.mars.robot.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mars.robot.dtos.ScentDto;
import com.mars.robot.models.Grid;
import com.mars.robot.models.Limit;
import com.mars.robot.models.Orientation;
import com.mars.robot.models.Robot;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MoveRobotServiceTests {
	
	@Autowired
	MoveRobotService moveRobotService;
	
	Grid grid;
	
	@Before
	public void setUp() {
		
		grid= new Grid();
		Limit limit = new Limit();
		limit.setyMax(3);
		limit.setxMax(5);
		
		grid.setLimit(limit);
		
		Robot robot = new Robot();
		robot.setStartPointX(1);
		robot.setStartPointY(1);
		robot.setEndPointX(1);
		robot.setEndPointY(1);
		robot.setInstructions("RFRFRFRF");
		Orientation orientation = new Orientation();
		orientation.setCurrentOrientation("e");
		robot.setOrientation(orientation);
		
		List<Robot> robots = new ArrayList<>();
		robots.add(robot);
		
		grid.setRobots(robots);

	}
	
	@Test
	public void move() {
		
		ScentDto dto = new ScentDto();
		dto.setOrientation("RFRFRFRF");
		ScentService scentService = mock(ScentService.class);
		Mockito.doNothing().when(scentService).addScent(dto);
		String result = moveRobotService.move(grid.getLimit(), grid.getRobots().get(0), grid.getGroupId());
		
		assertEquals("1 1 E", result);
		
	}
	
}
