package com.mars.robot.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.robot.models.Grid;
import com.mars.robot.models.Limit;
import com.mars.robot.models.Orientation;
import com.mars.robot.models.Robot;
import com.mars.robot.properties.ApplicationProperties;

@Service
public class ReadFileService {
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	public Grid readFromFile() {
		
		Grid grid = new Grid();
		List<String> lines = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(applicationProperties.getFilePath()))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	lines.add(line);
		    }
		    
		    if(lines.size() == 0) {
		    	throw new Exception("File is empty");
		    }
		    
		    String[] partOfLine = lines.get(0).split(" ");
    	    if(partOfLine.length != 2) {
    		   throw new Exception("Missing Limit");
    	    }
    	   
    	    int xMax = Integer.parseInt(partOfLine[0]);
    	    int yMax = Integer.parseInt(partOfLine[1]);
    	    
    	    Limit limit = new Limit();
    	    limit.setxMax(xMax);
    	    limit.setyMax(yMax);
    	    grid.setLimit(limit);
    	    
    	    List<Robot> robots = new ArrayList<>();
    	    
    	    for(int i=1; i<lines.size()-1;i+=2) {
    	    	
    	    	String[] partOfLinei = lines.get(i).split(" ");
    	    	if(partOfLinei.length != 3) {
    	    		throw new Exception("Missing Robot's start points");
    	    	}
    	    	
    	    	Robot robot = new Robot();
    	    	robot.setStartPointX(Integer.parseInt(partOfLinei[0]));
    	    	robot.setStartPointY(Integer.parseInt(partOfLinei[1]));
    	    	Orientation orientation = new Orientation();
    	    	orientation.getOrientation(partOfLinei[2]);
    	    	robot.setOrientation(orientation);
    	    	
    	    	robot.setInstructions(lines.get(i+1));
    	    	robots.add(robot);
    	    }
    	    
    	    grid.setRobots(robots);
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return grid;
	}
	
}
