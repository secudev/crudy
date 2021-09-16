package net.secudev.crudy.utils;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.secudev.crudy.model.produit.Produit;
import net.secudev.crudy.model.produit.ProduitRepository;

@Component
public class Populator {

	@Autowired
	private ProduitRepository produits;

	public void initProduit(int max) {
		produits.deleteAll();
		Random rnd = new Random();	
		for (int i = 0; i < max; i++) {
			float pa = (float)rnd.nextInt(100);
            Produit p = new Produit("produit-" + i, "Description du produit "+ i,pa,pa*2,rnd.nextInt(1000), LocalDateTime.now().minusDays(rnd.nextInt(25)).toLocalDate());
			produits.save(p);
		}
	}
}