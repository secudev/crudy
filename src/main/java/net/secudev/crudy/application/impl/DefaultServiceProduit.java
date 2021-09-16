package net.secudev.crudy.application.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.secudev.crudy.application.ServiceProduit;
import net.secudev.crudy.model.produit.Produit;
import net.secudev.crudy.model.produit.ProduitRepository;
import net.secudev.crudy.utils.Populator;

@Service
public class DefaultServiceProduit implements ServiceProduit {

	@Autowired
	private ProduitRepository produits;

	@Autowired
	private Populator populator;

	@Override
	@Transactional
	public void populateRandom() {
		populator.initProduit(100);
	}

	@Override
	@Transactional
	public void deleteAll() {
		produits.deleteAll();
	}

	@Override
	public Map<String, Integer> statStocks() {
		
		Map<String, Integer> data = new LinkedHashMap<>();
		for (Produit p : produits.findAll(Sort.by(Sort.Direction.DESC, "stock"))) {
			data.put(p.getLibelle(), p.getStock());
		}
		
		return data;
	}

	@Override
	public Page<Produit> findAllPage(PageRequest pr) {
		return produits.findAll(pr);
	}

	@Override
	@Transactional
	public void save(Produit produit) {
		produits.save(new Produit(produit.getLibelle(), produit.getDescription(), produit.getPrixAchat(),
				produit.getPrixVente(), produit.getStock(), produit.getDateAchat()));
	}
	
	@Override
	public void update(Produit produit) {
		produits.save(produit);		
	}

	@Override
	public Produit findProduitById(String id) {
		return produits.findById(id).orElseThrow(() -> new IllegalArgumentException("Id produit invalide: " + id));
	}

	@Override
	public void deleteProduitById(String id) {
		Produit p = findProduitById(id);
		produits.delete(p);
	}	
}
