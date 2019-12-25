package com.yassine.app.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Client implements Serializable {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@OneToMany(targetEntity = Contrat.class, cascade= CascadeType.ALL)
	@JoinColumn(name = "idClient", referencedColumnName="id")
	private List<Contrat> listContrats;
	
	@OneToMany(targetEntity = Logement.class, cascade= CascadeType.ALL)
	@JoinColumn(name = "idClient", referencedColumnName="id")
	private List<Contrat> listLogement;

	public Client() {
		super();
	}

	public Client(String nom, String email, String password, List<Contrat> listContrats, List<Contrat> listLogement) {
		super();
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.listContrats = listContrats;
		this.listLogement = listLogement;
	}

	public Client(String nom, String email, String password) {
		super();
		this.nom = nom;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
