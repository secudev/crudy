package net.secudev.crudy.model.produit;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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
	@NotBlank(message = "Le libellé est obligatoire")
	private String libelle;
	private String description;	
	@NotNull(message = "Le prix d'achat est obligatoire")
	private float prixAchat;
	private int stock;
	//Nécessaire pour la convertion de date dans form avec thymeleaf, de plus la date devrait avoir 2 accolades ex : *{{dateAchat}}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateAchat;
}

