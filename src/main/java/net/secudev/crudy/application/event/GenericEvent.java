package net.secudev.crudy.application.event;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GenericEvent extends ApplicationEvent{
	
	private String action;
	private String utilisateur;
	private LocalDateTime date;

	private static final long serialVersionUID = 1L;

	public GenericEvent(Object source,String action,String utilisateur,LocalDateTime date) {
		super(source);
		this.action=action;
		this.utilisateur=utilisateur;
		this.date=date;
	}

}
