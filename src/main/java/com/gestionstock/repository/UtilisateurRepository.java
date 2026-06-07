package com.gestionstock.repository;

import com.gestionstock.model.Utilisateur;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository d'accès aux utilisateurs.
 */
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByUsernameAndActifTrue(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
