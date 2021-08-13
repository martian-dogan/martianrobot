package com.mars.robot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.robot.dtos.ScentDto;
import com.mars.robot.models.Scent;
import com.mars.robot.repositories.ScentRepository;

@Service
public class ScentService {
	
	@Autowired
	ScentRepository scentRepository;
	
	public void addScent(ScentDto dto) {
		Scent scent = new Scent();
		scent.setGroupId(dto.getGroupId());
		scent.setOrientation(dto.getOrientation());
		scent.setX(dto.getX());
		scent.setY(dto.getY());
		scentRepository.saveAndFlush(scent);
		System.out.println("Lost point added -- X:"+dto.getX()+" Y:"+dto.getY()+ " Orientation: "+dto.getOrientation());
	}
	
	public List<ScentDto> getAllScent() {
		List<Scent> scentList = scentRepository.findAll();
		return convertToDto(scentList);
	}
	
	
	private List<ScentDto> convertToDto(List<Scent> scentList){
		
		List<ScentDto> resultList = new ArrayList<>();
		for(Scent scent: scentList) {
			ScentDto dto = new ScentDto();
			dto.setGroupId(scent.getGroupId());
			dto.setOrientation(scent.getOrientation());
			dto.setX(scent.getX());
			dto.setY(scent.getY());
		}
		
		return resultList;
	}
	
}
