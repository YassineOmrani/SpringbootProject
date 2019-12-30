package com.yassine.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yassine.app.entities.Agence;
import com.yassine.app.entities.Client;
import com.yassine.app.entities.Contrat;
import com.yassine.app.entities.Logement;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
	
	Contrat findByAgence(Agence a);
	Page<Contrat> findByAgence(Agence c,Pageable p);
	Optional<Contrat> findByClient(Client c);
	Contrat findByLogement(Logement l);
	Page<Contrat> findAll(Pageable p);
	Page<Contrat> findByClient(Client c,Pageable p);
}
