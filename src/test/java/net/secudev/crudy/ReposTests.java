package net.secudev.crudy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import net.secudev.crudy.model.ProduitRepository;
import net.secudev.crudy.utils.Populator;

@SpringBootTest
@Log4j2
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
		log.info(produits.count());
		assertThat(produits.count()).isEqualTo(100);
	}

}
