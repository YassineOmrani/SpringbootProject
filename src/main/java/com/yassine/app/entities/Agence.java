package com.yassine.app.entities;

import java.io.Serializable;
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
	@GeneratedValue
	private int id;
	
	@Column(name="nom")
	private String nom;
	
}
