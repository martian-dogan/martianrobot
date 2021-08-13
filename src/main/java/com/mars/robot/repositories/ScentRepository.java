package com.mars.robot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mars.robot.models.*;

@Repository
public interface ScentRepository extends JpaRepository<Scent, Integer> {
	
}
