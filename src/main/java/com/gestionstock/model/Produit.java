package com.gestionstock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entité produit avec état du stock.
 */
@Entity
@Table(name = "produits")
@Getter
@Setter
@NoArgsConstructor
public class Produit {

    public enum EtatStock {
        DISPONIBLE,
        FAIBLE,
        RUPTURE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false, length = 150)
    private String nom;

    @NotBlank(message = "La référence est obligatoire")
    @Column(nullable = false, unique = true, length = 40)
    private String reference;

    @Column(length = 800)
    private String description;

    @NotBlank(message = "La catégorie est obligatoire")
    @Column(nullable = false, length = 80)
    private String categorie;

    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.01", message = "Le prix doit être supérieur à 0")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal prix;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 0, message = "La quantité ne peut pas être négative")
    @Column(nullable = false)
    private Integer quantite;

    @NotNull(message = "La quantité minimale est obligatoire")
    @Min(value = 0, message = "La quantité minimale ne peut pas être négative")
    @Column(nullable = false)
    private Integer quantiteMinimale = 5;

    @NotBlank(message = "Le fournisseur est obligatoire")
    @Column(nullable = false, length = 100)
    private String fournisseur;

    @Column(nullable = false)
    private Boolean actif = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(nullable = false)
    private LocalDateTime dateModification;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        dateCreation = now;
        dateModification = now;
    }

    @PreUpdate
    public void preUpdate() {
        dateModification = LocalDateTime.now();
    }

    public EtatStock getEtatStock() {
        if (quantite == null || quantite <= 0) {
            return EtatStock.RUPTURE;
        }
        if (quantiteMinimale != null && quantite <= quantiteMinimale) {
            return EtatStock.FAIBLE;
        }
        return EtatStock.DISPONIBLE;
    }
}
