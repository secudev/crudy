package net.secudev.crudy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import net.secudev.crudy.model.produit.Produit;
import net.secudev.crudy.model.produit.ProduitRepository;
import net.secudev.crudy.utils.Populator;

@SpringBootTest
public class ReposTests {

	@Autowired
	private ProduitRepository produits;
	
	@Autowired
	private Populator populator;

	@BeforeEach
	private void avantChaqueTest() {
		produits.deleteAll();
	}

	@Test
	public void initDataRenvoieLebonNombreDePoduits() {
		populator.initProduit(100);		
		assertThat(produits.count()).isEqualTo(100);
	}

	@Test
	public void creationConformeDunProduit() {
		Produit p1 = new Produit("p1", "Le produit 1 sert à tralala", 20.5f, 10, LocalDateTime.now().minusDays(50).toLocalDate());
		produits.save(p1);
		assertTrue(produits.findAll().get(0).getId().equals(p1.getId()));
		assertTrue(produits.findAll().get(0).getDescription().equals(p1.getDescription()));
		assertTrue(produits.findAll().get(0).getPrixAchat() == p1.getPrixAchat());
		// etc
	}

	@Test
	public void deuxProduitAvecLeMemeLibelleNePeuventPasExister() {
		
		Produit p1 = new Produit("p1", "Le produit 1 sert à tralala", 20.5f, 10, LocalDateTime.now().minusDays(50).toLocalDate());
		Produit p2 = new Produit("p1", "Le produit 1 sert à tralala", 20.5f, 10, LocalDateTime.now().minusDays(50).toLocalDate());
		produits.save(p1);

		//Capture de l'exception dans un objet
		Throwable thrown = catchThrowable(() -> {
			produits.save(p2);
		});
		
		//Confirmation du type de l'exception et d'une chaine contenue dans son message
		assertThat(thrown).isInstanceOf(DataIntegrityViolationException.class).hasMessageContaining("PRODUIT(LIBELLE)");
	}
}
