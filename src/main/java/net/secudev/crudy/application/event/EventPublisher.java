package net.secudev.crudy.application.event;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public void publishGenericEvent(final String action, final String utilisateur) {
		GenericEvent event = new GenericEvent(this, action, utilisateur, LocalDateTime.now());
		applicationEventPublisher.publishEvent(event);
	}
}
