package com.gestionstock.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gestionstock.dto.ProduitDTO;
import com.gestionstock.dto.UtilisateurDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class AccessorsTest {

    @Test
    void shouldReadAndWriteProduitFields() {
        Produit produit = new Produit();
        LocalDateTime dateCreation = LocalDateTime.now().minusDays(1);
        LocalDateTime dateModification = LocalDateTime.now();

        produit.setId(1L);
        produit.setNom("Ordinateur");
        produit.setReference("REF-001");
        produit.setDescription("Portable");
        produit.setCategorie("Informatique");
        produit.setPrix(new BigDecimal("1500.00"));
        produit.setQuantite(10);
        produit.setQuantiteMinimale(2);
        produit.setFournisseur("ACME");
        produit.setActif(true);
        produit.setDateCreation(dateCreation);
        produit.setDateModification(dateModification);

        assertEquals(1L, produit.getId());
        assertEquals("Ordinateur", produit.getNom());
        assertEquals("REF-001", produit.getReference());
        assertEquals("Portable", produit.getDescription());
        assertEquals("Informatique", produit.getCategorie());
        assertEquals(new BigDecimal("1500.00"), produit.getPrix());
        assertEquals(10, produit.getQuantite());
        assertEquals(2, produit.getQuantiteMinimale());
        assertEquals("ACME", produit.getFournisseur());
        assertEquals(true, produit.getActif());
        assertEquals(dateCreation, produit.getDateCreation());
        assertEquals(dateModification, produit.getDateModification());
    }

    @Test
    void shouldReadAndWriteProduitDtoFields() {
        ProduitDTO dto = new ProduitDTO();
        dto.setId(1L);
        dto.setNom("Ordinateur");
        dto.setReference("REF-001");
        dto.setDescription("Portable");
        dto.setCategorie("Informatique");
        dto.setPrix(new BigDecimal("1500.00"));
        dto.setQuantite(10);
        dto.setQuantiteMinimale(2);
        dto.setFournisseur("ACME");

        assertEquals(1L, dto.getId());
        assertEquals("Ordinateur", dto.getNom());
        assertEquals("REF-001", dto.getReference());
        assertEquals("Portable", dto.getDescription());
        assertEquals("Informatique", dto.getCategorie());
        assertEquals(new BigDecimal("1500.00"), dto.getPrix());
        assertEquals(10, dto.getQuantite());
        assertEquals(2, dto.getQuantiteMinimale());
        assertEquals("ACME", dto.getFournisseur());
    }

    @Test
    void shouldReadAndWriteUtilisateurFields() {
        Utilisateur utilisateur = new Utilisateur();
        Role role = new Role();
        LocalDateTime dateCreation = LocalDateTime.now().minusDays(1);
        LocalDateTime dateModification = LocalDateTime.now();

        utilisateur.setId(2L);
        utilisateur.setUsername("admin");
        utilisateur.setEmail("admin@test.com");
        utilisateur.setMotDePasse("secret");
        utilisateur.setNomComplet("Admin User");
        utilisateur.setRole(role);
        utilisateur.setActif(true);
        utilisateur.setDateCreation(dateCreation);
        utilisateur.setDateModification(dateModification);

        assertEquals(2L, utilisateur.getId());
        assertEquals("admin", utilisateur.getUsername());
        assertEquals("admin@test.com", utilisateur.getEmail());
        assertEquals("secret", utilisateur.getMotDePasse());
        assertEquals("Admin User", utilisateur.getNomComplet());
        assertEquals(role, utilisateur.getRole());
        assertEquals(true, utilisateur.getActif());
        assertEquals(dateCreation, utilisateur.getDateCreation());
        assertEquals(dateModification, utilisateur.getDateModification());
    }

    @Test
    void shouldReadAndWriteUtilisateurDtoFields() {
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setUsername("admin");
        dto.setEmail("admin@test.com");
        dto.setMotDePasse("secret");
        dto.setNomComplet("Admin User");
        dto.setRole("ROLE_ADMIN");

        assertEquals("admin", dto.getUsername());
        assertEquals("admin@test.com", dto.getEmail());
        assertEquals("secret", dto.getMotDePasse());
        assertEquals("Admin User", dto.getNomComplet());
        assertEquals("ROLE_ADMIN", dto.getRole());
    }
}
