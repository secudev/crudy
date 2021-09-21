package net.secudev.crudy.model.utilisateur;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.secudev.crudy.model.shared.AEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public final class Utilisateur extends AEntity {
	
	@Column(unique = true)
	private String login;
	private String password;
	@Column(unique = true)
	private String email;	
	//TRuc le plus simple, sinon il faut céer une class role etc, dans ce cas çà suffit
	private Boolean isAdmin;
	private LocalDateTime derniereConnexion = null;
	private String derniereIpConnue=null;
}
