package net.secudev.crudy.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produit extends AEntity{

	@NonNull
	@Column(unique = true, nullable = false)
	private String libelle;
	private String description;	
	private float prixAchat;
	private int stock;
	private LocalDateTime dateAchat;
}

