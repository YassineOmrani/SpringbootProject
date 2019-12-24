
package com.yassine.app.entities;
import javax.persistence.*;
import java.io.*;


@Entity
@Table(name="contrats")
public class Contrat implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "idClient")
	private int idClient;
	
	@Column(name = "idLogement")
	private int idLogement;
	
	@Column(name = "idAgence")
	private int idAgence;
	
	// Duree is an integer
	// Because renting is gonna be per month
	@Column(name = "duree")
	private int duree;
	
}
