package net.secudev.crudy.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;
import net.secudev.crudy.application.impl.DefaultServiceProduit;
import net.secudev.crudy.model.produit.Produit;

@Controller
@Log4j2
public class ProduitController extends AController {	

	@Autowired
	private DefaultServiceProduit serviceProduit;

	@GetMapping("produit/populate")
	public String populate() {
		serviceProduit.populateRandom();
		return "redirect:/produit/liste";
	}

	@Secured("ROLE_admin")
	@GetMapping("produit/destroy")
	public String destroy() {
		serviceProduit.deleteAll();
		return "redirect:/produit/liste";
	}

	@GetMapping("produit/stats")
	public String stats(Model model) {
		model.addAttribute("tab", serviceProduit.statStocks());
		return "produit/stats";
	}

	
	@GetMapping("produit/liste")
	public String listProduits(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, HttpServletResponse response) {

		Page<Produit> pageProduits = serviceProduit
				.findAllPage(PageRequest.of(page, size, Direction.DESC, "stock", "prixAchat"));
		
		model.addAttribute("pageProduits", pageProduits);
		model.addAttribute("pageNumber", page);
		model.addAttribute("size", size);
		model.addAttribute("totalElements", pageProduits.getTotalElements());
		model.addAttribute("totalPages", pageProduits.getTotalPages());
		model.addAttribute("pages", new int[pageProduits.getTotalPages()]);

		// nombre de bouttons de pages -1
		model.addAttribute("buttonPages", 4);
		
		
		Cookie c1 = new Cookie("param1",  java.util.Base64.getEncoder().encodeToString("value encoden in base64".getBytes()));
		
		c1.setDomain("localhost");
		c1.setPath("/");
		c1.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
		c1.setHttpOnly(true);//restrient la lecture seulement via http et pas JS
		
		Cookie c2 = new Cookie("param2", "value2");//
		c2.setSecure(true);

		//add cookie to response
		response.addCookie(c1);
		response.addCookie(c2);
		
		return "/produit/liste";
	}

	@PostMapping("/produit/creation")
	public String addUser(@Valid Produit produit, BindingResult result, Model model) {

		if (result.hasErrors()) {
			log.info(result.getAllErrors());
			return "produit/creation";
		}

		serviceProduit.save(produit);
		return "redirect:/produit/liste";
	}

	@GetMapping("/produit/creation")
	public String showAjouterProduit(Model model) {
		model.addAttribute("produit", new Produit());
		return "produit/creation";
	}

	@GetMapping("/produit/modification/{id}")
	public String editProduit(@PathVariable("id") String id, Model model) {

		Produit produit = serviceProduit.findProduitById(id);		
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

		serviceProduit.update(produit);

		return "redirect:/produit/liste";
	}

	@Secured("ROLE_admin")
	@GetMapping("/produit/delete/{id}")
	public String deleteProduit(@PathVariable("id") String id, Model model) {
		serviceProduit.deleteProduitById(id);
		return "redirect:/produit/liste";
	}
}
