package com.gestionstock.service;

import com.gestionstock.model.Produit;
import com.gestionstock.repository.ProduitRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service métier des produits (CRUD, recherche, filtres).
 */
@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public Page<Produit> rechercher(String terme, String categorie, String etat, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        String termeNettoye = (terme == null || terme.isBlank()) ? null : terme.trim();
        String categorieNettoyee = (categorie == null || categorie.isBlank()) ? null : categorie.trim();
        String etatNettoye = (etat == null || etat.isBlank()) ? null : etat.trim().toUpperCase();
        return produitRepository.rechercher(termeNettoye, categorieNettoyee, etatNettoye, pageable);
    }

    public List<Produit> categoriesDistinctes() {
        return produitRepository.findDistinctCategories().stream().map(c -> {
            Produit p = new Produit();
            p.setCategorie(c);
            return p;
        }).toList();
    }

    public List<String> listeCategories() {
        return produitRepository.findDistinctCategories();
    }

    public Produit save(Produit produit) {
        return produitRepository.save(produit);
    }

    public Optional<Produit> findById(Long id) {
        return produitRepository.findById(id).filter(Produit::getActif);
    }

    public Optional<Produit> findByReference(String reference) {
        return produitRepository.findByReference(reference);
    }

    public void supprimerLogiquement(Long id) {
        produitRepository.findById(id).ifPresent(produit -> {
            produit.setActif(false);
            produitRepository.save(produit);
        });
    }

    public long countDisponibles() {
        return produitRepository.findByActifTrue().size();
    }

    public long countRupture() {
        return produitRepository.findByActifTrueAndQuantiteEquals(0).size();
    }

    public long countStockFaible() {
        return produitRepository.findStockFaible().size();
    }
}
