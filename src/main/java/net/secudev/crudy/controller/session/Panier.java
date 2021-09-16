package net.secudev.crudy.controller.session;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.Data;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Data
public class Panier {	
	private final String user=SecurityContextHolder.getContext().getAuthentication().getName();
	private int nombre;
	private List<String> liste = new ArrayList<>();
}