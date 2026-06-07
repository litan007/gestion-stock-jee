package com.gestionstock.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ProduitTest {

    @Test
    void shouldReturnRuptureWhenQuantityZero() {
        Produit produit = new Produit();
        produit.setQuantite(0);
        produit.setQuantiteMinimale(5);

        assertEquals(Produit.EtatStock.RUPTURE, produit.getEtatStock());
    }

    @Test
    void shouldReturnFaibleWhenQuantityAtThreshold() {
        Produit produit = new Produit();
        produit.setQuantite(5);
        produit.setQuantiteMinimale(5);

        assertEquals(Produit.EtatStock.FAIBLE, produit.getEtatStock());
    }

    @Test
    void shouldReturnDisponibleWhenQuantityAboveThreshold() {
        Produit produit = new Produit();
        produit.setQuantite(20);
        produit.setQuantiteMinimale(5);

        assertEquals(Produit.EtatStock.DISPONIBLE, produit.getEtatStock());
    }
}
