package net.secudev.crudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping(value = {"/","index"})
	public String index(Model model)
	{
		model.addAttribute("motd","Bienvenue chers clients sur notre site en construction, encore un peu de patience et nous serons prêts à vous proposer blah bla blah....");
		return "index";
	}
}
