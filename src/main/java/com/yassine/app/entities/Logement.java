package com.yassine.app.entities;

import javax.persistence.*;
import lombok.*;


@Entity
@Table(name = "logements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Logement {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id ;
	
    @Column(name="adress")
    private String adress;
    
	@Column(name = "idAgence")
	private int idAgence;
	
	@Column(name="idClient")
	private int idClient;
	
	@Column(name="dispo")
	private boolean dispo;

	public Logement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Logement(String adress, int idAgence, int idClient, boolean dispo) {
		super();
		this.adress = adress;
		this.idAgence = idAgence;
		this.idClient = idClient;
		this.dispo = dispo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(int idAgence) {
		this.idAgence = idAgence;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public boolean isDispo() {
		return dispo;
	}

	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}
	
	
	
}
