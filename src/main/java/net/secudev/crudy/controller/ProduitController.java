package net.secudev.crudy.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.secudev.crudy.model.produit.Produit;
import net.secudev.crudy.model.produit.ProduitRepository;
import net.secudev.crudy.utils.Populator;

@Controller
public class ProduitController extends AController {

	@Autowired
	private ProduitRepository produits;

	@Autowired
	private Populator populator;

	@GetMapping("produit/populate")
	public String populate() {
		populator.initProduit(300);
		return "redirect:/produit/liste";
	}

	@GetMapping("produit/destroy")
	public String destroy() {
		produits.deleteAll();
		return "redirect:/produit/liste";
	}

	@GetMapping("produit/stats")
	public String stats(Model model) {
		
		Map<String, Integer> tab = new LinkedHashMap<>();

		for (Produit p : produits.findAll(Sort.by(Sort.Direction.DESC, "stock"))) {			
			tab.put(p.getLibelle(), p.getStock());
		}
		
		System.out.println(tab);
		model.addAttribute("tab", tab);

		return "produit/stats";
	}

	@GetMapping("produit/liste")
	public String listProduits(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<Produit> pageProduits = produits.findAll(PageRequest.of(page, size, Direction.DESC, "stock", "prixAchat"));
		model.addAttribute("pageProduits", pageProduits);
		model.addAttribute("pageNumber", page);
		model.addAttribute("size", size);
		model.addAttribute("totalElements", pageProduits.getTotalElements());
		model.addAttribute("totalPages", pageProduits.getTotalPages());
		model.addAttribute("pages", new int[pageProduits.getTotalPages()]);

		// nombre de bouttons de pages -1
		model.addAttribute("buttonPages", 9);

		return "/produit/liste";
	}

}
