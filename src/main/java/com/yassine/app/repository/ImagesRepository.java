package com.yassine.app.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yassine.app.entities.*;


public interface ImagesRepository extends JpaRepository<Images, Long>{
	
	@Query(value="SELECT * FROM images WHERE logement_id= :id ",nativeQuery=true)
	Optional<Images> findByLogementId(@Param("id")long logementId);
	
	@Query(value="DELETE * FROM images WHERE logement_id= :id ",nativeQuery=true)
	Optional<Images> DeleteByLogementId(@Param("id")long logementId);
}
