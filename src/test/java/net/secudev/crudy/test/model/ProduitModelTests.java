package net.secudev.crudy.test.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import net.secudev.crudy.model.produit.Produit;

@SpringBootTest
@Log4j2
public class ProduitModelTests {
	
	@Test
	public void deuxProduitsSontEgauxMemeSiLeurIdEstDifferent()	{
		Produit p1 = new Produit("p1", "Le produit 1 sert à tralala", 20.5f,30.10f, 10, LocalDateTime.now().minusDays(50).toLocalDate());
		Produit p2 = new Produit("p1", "Le produit 1 sert à tralala", 20.5f,30.10f, 10, LocalDateTime.now().minusDays(50).toLocalDate());
		assertThat(p1.getId()).isNotEqualTo(p2.getId());
		assertThat(p1).isEqualTo(p2);
		log.info(p1.equals(p2));
		//ceci grace à leur methode equals redefinie par lombok hors de la classe abstraite Aentity grace a @EqualsAndHashCode(callSuper = false) dans produit.
		
	}
}
