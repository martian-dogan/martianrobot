package com.mars.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.mars.robot.models.Grid;
import com.mars.robot.models.Robot;
import com.mars.robot.services.MoveRobotService;
import com.mars.robot.services.ReadFileService;

@Configuration
@EnableCaching
@ComponentScan("com.mars.*")
@EnableJpaRepositories(basePackages = { "com.mars" })
@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
public class MartianRobotsApplication {
	
	
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(MartianRobotsApplication.class, args);
		
		ReadFileService readFileService = applicationContext.getBean(ReadFileService.class);
		Grid grid = readFileService.readFromFile();
		
		MoveRobotService robotService = applicationContext.getBean(MoveRobotService.class);
		for(Robot robot: grid.getRobots()) {
			robot.setEndPointX(robot.getStartPointX());
			robot.setEndPointY(robot.getStartPointY());
			String result = robotService.move(grid.getLimit(), robot, grid.getGroupId());
			System.out.println("result: "+result);
		}
	}

}
