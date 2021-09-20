package net.secudev.crudy.model.activite;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiviteRepository extends JpaRepository<Activite, String> {
		
	Page<Activite> findByLogin(String login,Pageable p);
	Page<Activite> findByLoginIgnoreCase(String login, Pageable p);
	Page<Activite> findByActionContainsIgnoreCase(String keyword,Pageable p);
}
