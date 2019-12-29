
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
	
    
    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Logement logement;
	
	@ManyToOne
    @JoinColumn(name = "idAgence")
    private Agence agence;
	
	// Duree is an integer
	// Because renting is gonna be per month
	@Column(name = "duree")
	private int duree;

	
	
	
}
