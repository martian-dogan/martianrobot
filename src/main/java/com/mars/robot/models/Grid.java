package com.mars.robot.models;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Grid {
	
	private Limit limit;
	private List<Robot> robots;
	private String groupId= UUID.randomUUID().toString();
	
	public Limit getLimit() {
		return limit;
	}
	public void setLimit(Limit limit) {
		this.limit = limit;
	}
	public List<Robot> getRobots() {
		return robots;
	}
	public void setRobots(List<Robot> robots) {
		this.robots = robots;
	}
	public String getGroupId() {
		return groupId;
	}
	
	
}
