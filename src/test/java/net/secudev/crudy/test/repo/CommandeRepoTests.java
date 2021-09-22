package net.secudev.crudy.test.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import net.secudev.crudy.model.commande.CommandeRepository;
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
		Produit p3= produits.findAll().stream().skip(2).findFirst().get();
		log.info(user);
		log.info(p1);
		log.info(p2);
		log.info(p3);
	}

}
