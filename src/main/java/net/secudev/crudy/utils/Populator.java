package net.secudev.crudy.utils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import net.secudev.crudy.model.produit.Produit;
import net.secudev.crudy.model.produit.ProduitRepository;
import net.secudev.crudy.model.utilisateur.Utilisateur;
import net.secudev.crudy.model.utilisateur.UtilisateurRepository;

@Component
@Log4j2
public class Populator {

	@Autowired
	private ProduitRepository produits;

	@Autowired
	private UtilisateurRepository utilisateurs;

	@Autowired
	PasswordEncoder encoder;

	public void initUsers()  {
		if (utilisateurs.count() == 0) {
			Utilisateur user = new Utilisateur("user", encoder.encode("password"), "user@mail.com", false, null, null);
			Utilisateur admin = new Utilisateur("admin", encoder.encode("password"), "admin@mail.com", true, null,
					null);
			utilisateurs.saveAll(Arrays.asList(user, admin));
		} else
			log.error("InitUSers n est possible que s'il n'y a aucun utilisateur existant");
	}

	public void initProduit(int max) {
		produits.deleteAll();
		Random rnd = new Random();
		for (int i = 0; i < max; i++) {
			float pa = (float) rnd.nextInt(100);
			Produit p = new Produit("produit-" + i, "Description du produit " + i, pa, pa * 3, rnd.nextInt(1000),
					LocalDateTime.now().minusDays(rnd.nextInt(25)).toLocalDate());
			produits.save(p);
		}
	}

	
}