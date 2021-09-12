package net.secudev.crudy.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;
import net.secudev.crudy.model.produit.Produit;
import net.secudev.crudy.model.produit.ProduitRepository;
import net.secudev.crudy.utils.Populator;

@Controller
@Log4j2
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
		model.addAttribute("buttonPages", 4);

		return "/produit/liste";
	}

	@PostMapping("/produit/creation")
	public String addUser(@Valid Produit produit, BindingResult result, Model model) {

		if (result.hasErrors()) {
			log.info(result.getAllErrors());
			return "produit/creation";
		}

		produits.save(new Produit(produit.getLibelle(), produit.getDescription(), produit.getPrixAchat(),
				produit.getStock(), produit.getDateAchat()));
		
		return "redirect:/produit/liste";
	}

	@GetMapping("/produit/creation")
	public String showAjouterProduit(Model model) {
		model.addAttribute("produit", new Produit());
		return "produit/creation";
	}

	@GetMapping("/produit/modification/{id}")
	public String editProduit(@PathVariable("id") String id, Model model) {

		Produit produit = produits.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id produit invalide: " + id));
		model.addAttribute("produit", produit);
		return "produit/modification";
	}

	@PostMapping("/produit/{id}")
	public String updateProduit(@PathVariable("id") String id, @Valid Produit produit, BindingResult result,
			Model model) {

		
		if (result.hasErrors()) {
			produit.setId(id);
			return "produit/modification";
		}
		produits.save(produit);

		return "redirect:/produit/liste";
	}

	@GetMapping("/produit/delete/{id}")
	public String deleteProduit(@PathVariable("id") String id, Model model) {

		Produit produit = produits.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id produit invalide: " + id));
		produits.deleteById(produit.getId());

		return "redirect:/produit/liste";
	}
}
