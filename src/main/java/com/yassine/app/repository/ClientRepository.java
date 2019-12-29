package com.yassine.app.repository;

import com.yassine.app.entities.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	// Find Client by Email
	Optional<Client> findByEmail(String email);
	Optional<Client> findById(Long id);
}
