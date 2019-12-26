
package com.yassine.app.entities;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;


@Entity
@Table(name="contrats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contrat implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
    @Column(name="nom_agence")
    private String nom;
    
	@Column(name = "idClient")
	private int idClient;
	
	@OneToOne(targetEntity = Logement.class)
	@JoinColumn(name="id")
	private int idLogement;
	
	@Column(name = "idAgence")
	private int idAgence;
	
	// Duree is an integer
	// Because renting is gonna be per month
	@Column(name = "duree")
	private int duree;
	
	// Is gonna always be (Price = prix per months X nb of months)
	@Column(name= "prix")
	private float prix;

	
	
	
}
