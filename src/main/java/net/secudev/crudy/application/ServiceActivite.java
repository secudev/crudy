package net.secudev.crudy.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;

import net.secudev.crudy.model.activite.Activite;

public interface ServiceActivite {
	
	@Secured("ROLE_admin")
	Page<Activite> findAll(PageRequest pr);
	
	@Secured("ROLE_admin")
	Page<Activite> findByLogin(String login,PageRequest pr);
	
	@Secured("ROLE_admin")
	Page<Activite> findByActionContainsIgnoreCase(String keyword,PageRequest pr);

}
