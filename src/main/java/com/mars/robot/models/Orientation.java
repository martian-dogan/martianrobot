package com.mars.robot.models;

import java.util.Arrays;
import java.util.List;



public class Orientation {
	
	private final int[] North = new int[]{0,1};
	private final int[] South = new int[]{0,-1};
	private final int[] East = new int[]{1,0};
	private final int[] West = new int[]{-1,0};
	
	private String[] orientationOrder = new String[]{"n", "e", "s", "w"};
	
	private int[] currentOrientationPosition;
	private String currentOrientation;
	
	public void getOrientation(String orientation) {
		try {
			orientation = orientation.toLowerCase();
			setCurrentOrientation(orientation);
			switch (orientation) {
			case "n":
				setCurrentOrientationPosition(North);
				break;
			case "s":
				setCurrentOrientationPosition(South);
				break;
			case "e":
				setCurrentOrientationPosition(East);
				break;
			case "w":
				setCurrentOrientationPosition(West);
				break;
			default:
				throw new Exception("No such orientation found: "+orientation);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void newOrientation(char direction) {
		
		try {
			
			List<String> list = Arrays.asList(orientationOrder);
			int index = list.indexOf(currentOrientation);
			int newIndex = index;
			
			switch (direction) {
			case 'l':
				newIndex = index == 0 ? orientationOrder.length-1 : index-1;
				break;
			case 'r':
				newIndex = index == orientationOrder.length-1 ? 0 : index+1;
				break;
			default:
				throw new Exception("No direction other than L or R directions can be entered!");
			}
			
			getOrientation(list.get(newIndex));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	public int[] getCurrentOrientationPosition() {
		return currentOrientationPosition;
	}

	public void setCurrentOrientationPosition(int[] currentOrientationPosition) {
		this.currentOrientationPosition = currentOrientationPosition;
	}

	public String getCurrentOrientation() {
		return currentOrientation;
	}

	public void setCurrentOrientation(String currentOrientation) {
		this.currentOrientation = currentOrientation;
	}

	
}
