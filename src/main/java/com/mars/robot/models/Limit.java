package com.mars.robot.models;

import org.springframework.stereotype.Component;

@Component
public class Limit {
	
	private int xMax;
	private int xMin=0;
	private int yMax;
	private int yMin= 0;
	
	public int getxMax() {
		return xMax;
	}
	public void setxMax(int xMax) {
		this.xMax = xMax;
	}
	public int getxMin() {
		return xMin;
	}
	public void setxMin(int xMin) {
		this.xMin = xMin;
	}
	public int getyMax() {
		return yMax;
	}
	public void setyMax(int yMax) {
		this.yMax = yMax;
	}
	public int getyMin() {
		return yMin;
	}
	public void setyMin(int yMin) {
		this.yMin = yMin;
	}
	
	
	
	
}
