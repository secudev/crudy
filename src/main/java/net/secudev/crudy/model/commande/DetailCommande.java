package net.secudev.crudy.model.commande;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.secudev.crudy.model.produit.Produit;


@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailCommande {

	private String description;
	private int quantite;
	@ManyToOne
	private Produit produit;
}
