package com.yassine.app.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yassine.app.entities.*;


public interface ImagesRepository extends JpaRepository<Images, Long>{
	
}
