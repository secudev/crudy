package net.secudev.crudy.application;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;

import net.secudev.crudy.model.produit.Produit;

public interface ServiceProduit {
	
	@Secured("ROLE_admin")
	void  populateRandom();
	
	@Secured("ROLE_admin")
	void deleteAll();
	
	@Secured("ROLE_admin")
	Map<String, Integer> statStocks();	
	
	Page<Produit> findAllPage(PageRequest pr);
	
	@Secured("ROLE_admin")
	void save(Produit produit);
	
	@Secured("ROLE_admin")
	void update(Produit produit);
	
	Produit findProduitById(String id);
	
	@Secured("ROLE_admin")
	void deleteProduitById(String id);
}
