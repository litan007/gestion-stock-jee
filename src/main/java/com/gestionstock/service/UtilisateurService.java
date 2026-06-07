package com.gestionstock.service;

import com.gestionstock.model.Utilisateur;
import com.gestionstock.repository.UtilisateurRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service métier des utilisateurs.
 */
@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Optional<Utilisateur> findByUsername(String username) {
        return utilisateurRepository.findByUsernameAndActifTrue(username);
    }
}
