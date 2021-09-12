package net.secudev.crudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.secudev.crudy.config.AppGlobalProperties;

@Controller
public class IndexController {

	@Autowired	
	private  AppGlobalProperties properties;
	
	@Autowired
	private Environment environment;
	
	
	@GetMapping(value = {"/","index"})
	public String index(Model model){
		
		String profil = environment.getActiveProfiles().length==0?"default":environment.getActiveProfiles()[0];
		model.addAttribute("motd",properties.getMotd()+", l'application a été lancée avec le mode "+properties.getMode()+" et le profil "+profil);
		return "index";
	}
}
