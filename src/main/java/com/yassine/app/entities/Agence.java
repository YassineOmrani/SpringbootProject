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
	private long id;
    
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="nom")
	private String nom;
	@OneToMany(targetEntity = Logement.class, cascade= CascadeType.ALL)
	@JoinColumn(name = "idAgence", referencedColumnName="id")
	private List<Logement> listLogement;
	
	@OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    private List<Contrat> Contrats;
	
	
	
	
	
}
