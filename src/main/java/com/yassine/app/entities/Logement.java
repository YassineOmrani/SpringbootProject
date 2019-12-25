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
	
	@Column(name = "idAgence")
	private int idAgence;
	
	@Column(name="idClient")
	private int idClient;
	
	@Column(name="dispo")
	private boolean dispo;
	
}
