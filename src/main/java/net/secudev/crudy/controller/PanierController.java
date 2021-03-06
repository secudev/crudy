package net.secudev.crudy.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

import net.secudev.crudy.controller.session.Panier;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class PanierController {

	@Autowired
	private Panier panier;

	@GetMapping("/panier")
	public String affichePanier() {
		panier.getListe().add(UUID.randomUUID().toString());
		panier.setNombre(panier.getListe().size());
		return "panier";
	}
}
