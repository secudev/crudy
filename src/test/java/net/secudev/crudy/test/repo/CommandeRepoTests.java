package net.secudev.crudy.test.repo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import net.secudev.crudy.model.commande.Commande;
import net.secudev.crudy.model.commande.CommandeRepository;
import net.secudev.crudy.model.commande.DetailCommande;
import net.secudev.crudy.model.produit.Produit;
import net.secudev.crudy.model.produit.ProduitRepository;
import net.secudev.crudy.model.utilisateur.Utilisateur;
import net.secudev.crudy.model.utilisateur.UtilisateurRepository;
import net.secudev.crudy.utils.Populator;

@SpringBootTest
@Log4j2
public class CommandeRepoTests {

	@Autowired
	private Populator populator;

	@Autowired
	private ProduitRepository produits;

	@Autowired
	private UtilisateurRepository utilisateurs;

	@Autowired
	private CommandeRepository commandes;

	@BeforeEach
	private void avantChaqueTest() throws Exception {
		produits.deleteAll();
		utilisateurs.deleteAll();
		commandes.deleteAll();
		populator.initProduit(10);
		populator.initUsers();
	}

	@Test
	public void creationCommandeJpaOk() {
		Utilisateur user = utilisateurs.findByLogin("user");
		Produit p1 = produits.findAll().stream().findFirst().get();
		Produit p2 = produits.findAll().stream().skip(1).findFirst().get();
		Produit p3 = produits.findAll().stream().skip(2).findFirst().get();

		Commande c1 = new Commande(user);
		DetailCommande dc1 = new DetailCommande(2, p1);
		DetailCommande dc2 = new DetailCommande(12, p2);
		DetailCommande dc3 = new DetailCommande(42, p3);
		
		c1.ajouterDetail(dc1);
		log.info(dc1.getTotal());
		c1.ajouterDetail(dc2);
		log.info(dc2.getTotal());
		c1.ajouterDetail(dc3);
		log.info(dc3.getTotal());

		commandes.save(c1);
		log.info(c1.getTotal());
		
		assertThat(c1.getTotal()).isEqualTo(dc1.getTotal() + dc2.getTotal() + dc3.getTotal());		

	}

}
