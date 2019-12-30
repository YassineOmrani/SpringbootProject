package com.yassine.app.entities;

import java.util.List;

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
	private Long id ;
	
    @Column(name="adress")
    private String adress;
    
	@Column(name = "idAgence")
	private Long idAgence;
	
	@Column(name="idClient")
	private Long idClient;
	
	@Column(name="dispo")
	private boolean dispo;
	
	@Column(name="prix")
	private float prix;
	
	@OneToOne(mappedBy = "logement")
    private Contrat contrat;
	
	@OneToMany(mappedBy = "logement", cascade = CascadeType.ALL)
    private List<Images> images;

}
