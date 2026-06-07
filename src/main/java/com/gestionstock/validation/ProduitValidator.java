package com.gestionstock.validation;

import com.gestionstock.dto.ProduitDTO;
import com.gestionstock.model.Produit;
import com.gestionstock.service.ProduitService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validateur personnalisé pour les règles métier des produits.
 */
@Component
public class ProduitValidator implements Validator {

    private final ProduitService produitService;

    public ProduitValidator(ProduitService produitService) {
        this.produitService = produitService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProduitDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProduitDTO dto = (ProduitDTO) target;
        if (dto.getPrix() != null && dto.getPrix().signum() <= 0) {
            errors.rejectValue("prix", "produit.prix", "Le prix doit être supérieur à 0");
        }
        if (dto.getQuantite() != null && dto.getQuantite() < 0) {
            errors.rejectValue("quantite", "produit.quantite", "La quantité ne peut pas être négative");
        }
        if (Validation.estTexteRenseigne(dto.getReference())) {
            Produit existant = produitService.findByReference(dto.getReference()).orElse(null);
            if (existant != null && (dto.getId() == null || !existant.getId().equals(dto.getId()))) {
                errors.rejectValue("reference", "produit.reference", "Cette référence existe déjà");
            }
        }
    }
}
