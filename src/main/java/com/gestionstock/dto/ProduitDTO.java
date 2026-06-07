package com.gestionstock.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de transfert pour les formulaires produit.
 */
@Getter
@Setter
public class ProduitDTO {

    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "La référence est obligatoire")
    private String reference;

    private String description;

    @NotBlank(message = "La catégorie est obligatoire")
    private String categorie;

    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.01", message = "Le prix doit être supérieur à 0")
    private BigDecimal prix;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 0, message = "La quantité doit être >= 0")
    private Integer quantite;

    @NotNull(message = "La quantité minimale est obligatoire")
    @Min(value = 0, message = "La quantité minimale doit être >= 0")
    private Integer quantiteMinimale;

    @NotBlank(message = "Le fournisseur est obligatoire")
    private String fournisseur;
}
