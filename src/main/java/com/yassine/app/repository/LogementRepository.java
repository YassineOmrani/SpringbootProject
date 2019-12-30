package com.yassine.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yassine.app.entities.Client;
import com.yassine.app.entities.Logement;

public interface LogementRepository extends JpaRepository<Logement, Long> {
	
	
	Optional<Logement> findByAdress(String adress);
	Page<Logement> findAll(Pageable p);
	List<Logement> findByIdAgence(Long id);
	Page<Logement> findByIdAgence(Long id,Pageable p);
	Page<Logement> findByDispo(boolean a,Pageable p);
	Optional<Logement> findById(Long id);
	Logement findTopByOrderByIdDesc();
	
}
