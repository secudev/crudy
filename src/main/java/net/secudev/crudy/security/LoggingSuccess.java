package net.secudev.crudy.security;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import net.secudev.crudy.application.event.EventPublisher;
import net.secudev.crudy.model.utilisateur.Utilisateur;
import net.secudev.crudy.model.utilisateur.UtilisateurRepository;

@Log4j2
@Component
public class LoggingSuccess extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	private UtilisateurRepository utilisateurs;
	
	@Autowired
	private EventPublisher eventPublisher;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		String lastIp=request.getRemoteAddr();
		Utilisateur target = utilisateurs.findByLogin(login);
		target.setDerniereConnexion(LocalDateTime.now());
		target.setDerniereIpConnue(lastIp);
		utilisateurs.save(target);
		log.info("Auth OK : "+login+" depuis l'IP "+lastIp);
		eventPublisher.publishGenericEvent("login OK depuis "+lastIp, login);
		
		super.onAuthenticationSuccess(request,response,authentication);
	}	
}