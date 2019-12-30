package com.yassine.app.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Images implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idImage")
	private long idImage;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Logement logement;
	
	@Column(name="path")
	private String path;
	
	
	
}
