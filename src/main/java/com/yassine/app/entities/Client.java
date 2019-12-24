package com.yassine.app.entities;

import java.io.Serializable;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nom")
	private float nom;
	
	@Column(name="email")
	private float email;
	
	@Column(name="password")
	private float password;
	
	
	
}
