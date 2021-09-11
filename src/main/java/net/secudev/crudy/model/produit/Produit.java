package net.secudev.crudy.model.produit;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import net.secudev.crudy.model.shared.AEntity;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Produit extends AEntity{

	@NonNull
	@Column(unique = true, nullable = false)
	private String libelle;
	private String description;	
	private float prixAchat;
	private int stock;
	private LocalDate dateAchat;
}

