package com.yassine.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yassine.app.entities.Agence;
import com.yassine.app.entities.Client;
import com.yassine.app.entities.Contrat;
import com.yassine.app.entities.Logement;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
	
	Contrat findByAgence(Agence a);
	Contrat findByClient(Client c);
	Contrat findByLogement(Logement l);
	Page<Contrat> findAll(Pageable p);
	
	
}
