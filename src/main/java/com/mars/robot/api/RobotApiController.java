package com.mars.robot.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mars.robot.dtos.ScentDto;
import com.mars.robot.services.ScentService;

@RestController
public class RobotApiController {

	@Autowired
	ScentService scentService;
	
	@RequestMapping(path = "/getAllLostPositions", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ScentDto>> getAllLostPositions(){
		HttpStatus status;
		List<ScentDto> listScent;
		try {
			listScent = scentService.getAllScent();
			status = HttpStatus.OK;
			
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			listScent = new ArrayList<>();
		}
		
		return ResponseEntity.status(status).body(listScent);
		
	}
	
}
