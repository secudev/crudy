package net.secudev.crudy.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class LoggingFailure extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {		
		// TODO : Tester si le user existe aussi pour detecter les bruteforce etc
		// TODO : possibilité de mettre en place un compteur de badlogin sur le compte utilisateur puis le bloquer au bout de X mauvais essais
		
		String login = request.getParameter("login");// nom du champs POSTé par le form de login
		String password = request.getParameter("password");
		log.warn(exception.getMessage() + " depuis l'IP " + request.getRemoteAddr() + ", utilisateur: "
				+ login+" password: "+password);
		super.onAuthenticationFailure(request, response, exception);
	}
}
