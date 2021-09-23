package net.secudev.crudy.model.commande;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.secudev.crudy.model.utilisateur.Utilisateur;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Commande extends net.secudev.crudy.model.shared.AEntity {

	private LocalDateTime dateCommande = LocalDateTime.now();
	@NonNull
	@ManyToOne
	private Utilisateur utilisateur;
	@ElementCollection
	private Set<DetailCommande> details = new HashSet<>();

	private float total = 0;

	public void ajouterDetail(@NonNull DetailCommande dc) {
		this.details.add(dc);
		total += dc.getTotal();
	}

	public void supprimerDetail(@NonNull DetailCommande dc) {
		if (this.details.contains(dc)) {
			total -= dc.getTotal();
			this.details.remove(dc);
		}
		// else throw ...
	}

}
