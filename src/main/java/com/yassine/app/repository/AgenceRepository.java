package com.yassine.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yassine.app.entities.Agence;

public interface AgenceRepository extends JpaRepository<Agence, Long>{
	
	// Find Agence by email
	Optional<Agence> findByEmail(String email);
	Optional<Agence> findById(Long id);
	
}
