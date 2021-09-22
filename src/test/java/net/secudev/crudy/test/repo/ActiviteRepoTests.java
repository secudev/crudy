package net.secudev.crudy.test.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import net.secudev.crudy.model.activite.Activite;
import net.secudev.crudy.model.activite.ActiviteRepository;

@SpringBootTest
public class ActiviteRepoTests {

	@Autowired
	private ActiviteRepository activites;


	@BeforeEach
	private void avantChaqueTest() {
		activites.deleteAll();
		activites.save(new Activite("user", "login OK depuis 127.0.0.1", LocalDateTime.now().minusDays(10)));
		activites.save(new Activite("user", "login OK depuis 192.168.2.110", LocalDateTime.now().minusDays(2)));
		activites.save(new Activite("admin", "login OK depuis 127.0.0.1", LocalDateTime.now().minusDays(0)));
		activites.save(new Activite("admin", "delete all produits", LocalDateTime.now().minusDays(10)));
	}

	@Test
	public void renvoieActiviteParLogin() {
	
		assertThat(activites.count()).isEqualTo(4);
		assertThat(activites.findByLogin("admin", PageRequest.of(0, 100)).getContent().size()).isEqualTo(2);
		assertThat(activites.findByLogin("user", PageRequest.of(0, 100)).getContent().size()).isEqualTo(2);
		assertThat(activites.findByActionContainsIgnoreCase("delete", PageRequest.of(0, 100)).getContent().size()).isEqualTo(1);
		assertThat(activites.findByActionContainsIgnoreCase("192.168.2.110", PageRequest.of(0, 100)).getContent().size()).isEqualTo(1);	

	}

}
