package net.secudev.crudy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.secudev.crudy.model.utilisateur.Utilisateur;
import net.secudev.crudy.model.utilisateur.UtilisateurRepository;

@SpringBootTest
public class AuthTests {

	@Autowired
	private UtilisateurRepository utilisateurs;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void creationUserConforme()	{
		
		Utilisateur user = new Utilisateur("user", encoder.encode("password"), "user@mail.com", false,null,null);
		Utilisateur admin = new Utilisateur("admin", encoder.encode("password"), "admin@mail.com", true,null,null);
		utilisateurs.saveAll(Arrays.asList(user,admin));
		assertThat(utilisateurs.count()).isEqualTo(2);	
		assertThat(utilisateurs.findByIsAdminTrue().size()).isEqualTo(1);
	}
}
