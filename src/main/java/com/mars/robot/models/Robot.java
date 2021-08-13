package com.mars.robot.models;

import org.springframework.stereotype.Component;

@Component
public class Robot {
	
	private int startPointX;
	private int startPointY;
	private Orientation orientation;
	private String instructions;
	private int endPointX;
	private int endPointY;
	
	
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public int getStartPointX() {
		return startPointX;
	}
	public void setStartPointX(int startPointX) {
		this.startPointX = startPointX;
	}
	public int getStartPointY() {
		return startPointY;
	}
	public void setStartPointY(int startPointY) {
		this.startPointY = startPointY;
	}
	public int getEndPointX() {
		return endPointX;
	}
	public void setEndPointX(int endPointX) {
		this.endPointX = endPointX;
	}
	public int getEndPointY() {
		return endPointY;
	}
	public void setEndPointY(int endPointY) {
		this.endPointY = endPointY;
	}
	
}
