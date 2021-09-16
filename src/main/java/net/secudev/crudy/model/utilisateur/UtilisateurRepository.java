package net.secudev.crudy.model.utilisateur;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String>{
	Utilisateur findByLoginIgnoreCase(String login);
	Utilisateur findByLogin(String login);
	Utilisateur findByEmail(String email);
	List<Utilisateur> findByIsAdminTrue();

}
