package net.secudev.crudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
		populator.initProduit(100);
		return "redirect:/produit/liste";
	}

	@GetMapping("produit/liste")
	public String listProduits(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {

		if(produits.count()==0) populator.initProduit(100);
		Page<Produit> pageProduits = produits.findAll(PageRequest.of(page, size, Direction.DESC, "stock","prixAchat"));
		model.addAttribute("pageProduits", pageProduits);
		model.addAttribute("pageNumber", page);
		model.addAttribute("size", size);
		model.addAttribute("totalElements", pageProduits.getTotalElements());
		model.addAttribute("totalPages", pageProduits.getTotalPages());
		model.addAttribute("pages", new int[pageProduits.getTotalPages()]);

		//nombre de bouttons de pages -1
		model.addAttribute("buttonPages", 9);

		return "/produit/liste";
	}

}
