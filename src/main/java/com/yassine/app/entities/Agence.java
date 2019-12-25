package com.yassine.app.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="agences")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agence implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="nom")
	private String nom;
	
	@OneToMany(targetEntity = Contrat.class, cascade= CascadeType.ALL)
	@JoinColumn(name = "idAgence", referencedColumnName="id")
	private List<Contrat> listContrats;
	
	@OneToMany(targetEntity = Logement.class, cascade= CascadeType.ALL)
	@JoinColumn(name = "idAgence", referencedColumnName="id")
	private List<Contrat> listLogement;


	// Contructor && Getters && Setters
	// Because lombok is useless and i hate Java
	
	public Agence() {
		super();
	}


	public Agence(String nom, List<Contrat> listContrats, List<Contrat> listLogement) {
		super();
		this.nom = nom;
		this.listContrats = listContrats;
		this.listLogement = listLogement;
	}


	public Agence(String nom) {
		super();
		this.nom = nom;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public List<Contrat> getListContrats() {
		return listContrats;
	}


	public void setListContrats(List<Contrat> listContrats) {
		this.listContrats = listContrats;
	}


	public List<Contrat> getListLogement() {
		return listLogement;
	}


	public void setListLogement(List<Contrat> listLogement) {
		this.listLogement = listLogement;
	}
	
	
	
	
}
