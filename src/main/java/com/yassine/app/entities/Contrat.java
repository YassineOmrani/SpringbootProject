
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

	public Contrat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contrat(String nom, int idClient, int idLogement, int idAgence, int duree, float prix) {
		super();
		this.nom = nom;
		this.idClient = idClient;
		this.idLogement = idLogement;
		this.idAgence = idAgence;
		this.duree = duree;
		this.prix = prix;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdLogement() {
		return idLogement;
	}

	public void setIdLogement(int idLogement) {
		this.idLogement = idLogement;
	}

	public int getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(int idAgence) {
		this.idAgence = idAgence;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}
	
	
	
	
	
}
