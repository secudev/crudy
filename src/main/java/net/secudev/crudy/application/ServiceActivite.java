package net.secudev.crudy.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import net.secudev.crudy.model.activite.Activite;

public interface ServiceActivite {
	
	Page<Activite> findAllPage(PageRequest pr);
	Page<Activite> findByLogin(String login,PageRequest pr);
	Page<Activite> findByActionContainsIgnoreCase(String keyword,PageRequest pr);

}
