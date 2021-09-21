package net.secudev.crudy.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import net.secudev.crudy.application.ServiceActivite;
import net.secudev.crudy.model.activite.Activite;
import net.secudev.crudy.model.activite.ActiviteRepository;

@Service
public class DefaultServiceActivite implements ServiceActivite {

	@Autowired
	private ActiviteRepository activites;

	@Override
	public Page<Activite> findAll(PageRequest pr) {
		return activites.findAll(pr);
	}

	@Override
	public Page<Activite> findByLogin(String login, PageRequest pr) {
		return activites.findByLogin(login,pr);
	}

	@Override
	public Page<Activite> findByActionContainsIgnoreCase(String keyword,PageRequest pr) {
		return activites.findByActionContainsIgnoreCase(keyword,pr);
	}

}
