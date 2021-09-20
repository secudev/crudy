package net.secudev.crudy.test.repo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import net.secudev.crudy.model.activite.ActiviteRepository;
import net.secudev.crudy.utils.Populator;

@SpringBootTest
public class ActiviteReposTests {

	@Autowired
	private ActiviteRepository activites;

	@Autowired
	private Populator populator;

	@BeforeEach
	private void avantChaqueTest() {
		activites.deleteAll();
	}

	@Test
	public void renvoieActiviteParLogin() {
		populator.initActivites();
		assertThat(activites.count()).isEqualTo(4);
		assertThat(activites.findByLogin("admin", PageRequest.of(0, 100)).getContent().size()).isEqualTo(2);
		assertThat(activites.findByLogin("user", PageRequest.of(0, 100)).getContent().size()).isEqualTo(2);
		assertThat(activites.findByActionContainsIgnoreCase("delete", PageRequest.of(0, 100)).getContent().size()).isEqualTo(1);
		assertThat(activites.findByActionContainsIgnoreCase("192.168.2.110", PageRequest.of(0, 100)).getContent().size()).isEqualTo(1);	

	}

}
