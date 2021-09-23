package net.secudev.crudy.model.commande;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.secudev.crudy.model.produit.Produit;

@Data
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailCommande {

	private String description;

	private int quantite;
	@ManyToOne
	private Produit produit;
	private float total = 0;

	public DetailCommande(@Min(1) int quantite, @NonNull Produit produit) {

		this.produit = produit;
		this.setQuantite(quantite);
	}

	public void setQuantite(int value) {
		quantite = value;
		total = produit.getPrixVente() * quantite;
	}
}
