package net.secudev.crudy.application;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import net.secudev.crudy.model.produit.Produit;

public interface ServiceProduit {
	
	void  populateRandom();
	
	void deleteAll();
	
	Map<String, Integer> statStocks();
	
	Page<Produit> findAllPage(PageRequest pr);
	
	void save(Produit produit);
	
	void update(Produit produit);
	
	Produit findProduitById(String id);
	
	void deleteProduitById(String id);
}
