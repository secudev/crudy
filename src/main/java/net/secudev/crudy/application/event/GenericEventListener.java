package net.secudev.crudy.application.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import net.secudev.crudy.model.activite.Activite;
import net.secudev.crudy.model.activite.ActiviteRepository;

@Component
@Log4j2
public class GenericEventListener implements ApplicationListener<GenericEvent> {

	@Autowired
	private ActiviteRepository activites;

	@Override
	public void onApplicationEvent(GenericEvent event) {

		activites.save(new Activite(event.getUtilisateur(), event.getAction(), event.getDate()));
		log.info(event.toString());
	}
}