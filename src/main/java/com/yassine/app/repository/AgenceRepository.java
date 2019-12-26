package com.yassine.app.repository;

import com.yassine.app.entities.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenceRepository extends JpaRepository<Agence, Long>{
	
	// Find Agence by email
	Optional<Agence> findByEmail(String email);
	
}
