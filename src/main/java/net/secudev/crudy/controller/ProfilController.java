package net.secudev.crudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilController extends AController {

	@GetMapping("/profil/moncompte")
	public String monCompte()
	{
		return "profil/moncompte";
	}
}
