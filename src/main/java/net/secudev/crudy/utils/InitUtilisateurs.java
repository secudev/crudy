package net.secudev.crudy.utils;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.secudev.crudy.model.utilisateur.Utilisateur;
import net.secudev.crudy.model.utilisateur.UtilisateurRepository;

@Component
public class InitUtilisateurs implements ApplicationRunner{
	
	@Autowired
	private UtilisateurRepository utilisateurs;
	
	@Autowired
	 PasswordEncoder encoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(utilisateurs.count()==0) {
		Utilisateur user = new Utilisateur("user", encoder.encode("password"), "user@mail.com", false,null,null);
		Utilisateur admin = new Utilisateur("admin", encoder.encode("password"), "admin@mail.com", true,null,null);
		utilisateurs.saveAll(Arrays.asList(user,admin));
		}
	}
}
