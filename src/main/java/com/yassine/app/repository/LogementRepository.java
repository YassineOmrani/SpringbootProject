package com.yassine.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yassine.app.entities.Client;
import com.yassine.app.entities.Logement;

public interface LogementRepository extends JpaRepository<Logement, Long> {
	
	
	Optional<Logement> findByAdress(String adress);
	Page<Logement> findAll(Pageable p);
	

	List<Logement> findByIdAgence(Long id);
	Page<Logement> findByIdAgence(Long id,Pageable p);
	Page<Logement> findByDispo(boolean a,Pageable p);
	Optional<Logement> findById(Long id);
	
	// Find last inserted Logement Row
	Logement findTopByOrderByIdDesc();
	
	@Query(value = "SELECT * FROM Logements l Where l.adress LIKE %:adress%" , nativeQuery=true)
	Page<Logement> findByAdress(@Param("adress")String adressText,Pageable p);
}
