package net.secudev.crudy.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import net.secudev.crudy.model.utilisateur.Utilisateur;
import net.secudev.crudy.model.utilisateur.UtilisateurRepository;


@Component
@Log4j2
public class JpaAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UtilisateurRepository utilisateurs;
	
	@Autowired
	PasswordEncoder passwordEncoder;


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.trace(authentication.toString());
		Utilisateur target = utilisateurs.findByLogin(authentication.getName());
		if (target == null)
			throw new BadCredentialsException("Utilisateur inconnu");

		if (!passwordEncoder.matches(authentication.getCredentials().toString(), target.getPassword()))
			throw new BadCredentialsException("Mauvais mot de passe");

		List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_user"));

		if (target.getIsAdmin()) {
			roles.add(new SimpleGrantedAuthority("ROLE_admin"));
		}

		User user = new User(target.getLogin(), "", roles);

		log.trace(user.getPassword());
		
		return new UsernamePasswordAuthenticationToken(user, null, roles);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
