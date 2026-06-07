package com.gestionstock.controller;

import com.gestionstock.dto.ProduitDTO;
import com.gestionstock.model.Produit;
import com.gestionstock.service.ProduitService;
import com.gestionstock.validation.ProduitValidator;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Contrôleur MVC de gestion des produits.
 */
@Controller
@RequestMapping("/produits")
public class ProduitController {

    private final ProduitService produitService;
    private final ProduitValidator produitValidator;

    public ProduitController(ProduitService produitService, ProduitValidator produitValidator) {
        this.produitService = produitService;
        this.produitValidator = produitValidator;
    }

    @InitBinder("produit")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(produitValidator);
    }

    @GetMapping
    public String liste(@RequestParam(defaultValue = "") String terme,
                        @RequestParam(defaultValue = "") String categorie,
                        @RequestParam(defaultValue = "") String etat,
                        @RequestParam(defaultValue = "0") int page,
                        Model model) {
        Page<Produit> produits = produitService.rechercher(terme, categorie, etat, page, 10);
        model.addAttribute("produits", produits);
        model.addAttribute("terme", terme);
        model.addAttribute("categorie", categorie);
        model.addAttribute("etat", etat);
        model.addAttribute("categories", produitService.listeCategories());
        return "produits/liste";
    }

    @GetMapping("/ajouter")
    public String afficherAjout(Model model) {
        ProduitDTO dto = new ProduitDTO();
        dto.setQuantite(0);
        dto.setQuantiteMinimale(5);
        model.addAttribute("produit", dto);
        return "produits/ajouter";
    }

    @PostMapping("/ajouter")
    public String ajouter(@Valid @ModelAttribute("produit") ProduitDTO dto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "produits/ajouter";
        }
        produitService.save(toEntity(dto, new Produit()));
        redirectAttributes.addFlashAttribute("successMessage", "Produit ajouté avec succès");
        return "redirect:/produits";
    }

    @GetMapping("/editer/{id}")
    public String afficherEdition(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return produitService.findById(id)
            .map(produit -> {
                model.addAttribute("produit", toDto(produit));
                return "produits/editer";
            })
            .orElseGet(() -> {
                redirectAttributes.addFlashAttribute("errorMessage", "Produit introuvable");
                return "redirect:/produits";
            });
    }

    @PostMapping("/editer/{id}")
    public String editer(@PathVariable Long id,
                         @Valid @ModelAttribute("produit") ProduitDTO dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        dto.setId(id);
        if (bindingResult.hasErrors()) {
            return "produits/editer";
        }

        return produitService.findById(id)
            .map(existant -> {
                produitService.save(toEntity(dto, existant));
                redirectAttributes.addFlashAttribute("successMessage", "Produit modifié avec succès");
                return "redirect:/produits";
            })
            .orElseGet(() -> {
                redirectAttributes.addFlashAttribute("errorMessage", "Produit introuvable");
                return "redirect:/produits";
            });
    }

    @PostMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        produitService.supprimerLogiquement(id);
        redirectAttributes.addFlashAttribute("successMessage", "Produit supprimé avec succès");
        return "redirect:/produits";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return produitService.findById(id)
            .map(produit -> {
                model.addAttribute("produit", produit);
                return "produits/detail";
            })
            .orElseGet(() -> {
                redirectAttributes.addFlashAttribute("errorMessage", "Produit introuvable");
                return "redirect:/produits";
            });
    }

    private Produit toEntity(ProduitDTO dto, Produit produit) {
        produit.setNom(dto.getNom());
        produit.setReference(dto.getReference());
        produit.setDescription(dto.getDescription());
        produit.setCategorie(dto.getCategorie());
        produit.setPrix(dto.getPrix());
        produit.setQuantite(dto.getQuantite());
        produit.setQuantiteMinimale(dto.getQuantiteMinimale());
        produit.setFournisseur(dto.getFournisseur());
        if (produit.getActif() == null) {
            produit.setActif(true);
        }
        return produit;
    }

    private ProduitDTO toDto(Produit produit) {
        ProduitDTO dto = new ProduitDTO();
        dto.setId(produit.getId());
        dto.setNom(produit.getNom());
        dto.setReference(produit.getReference());
        dto.setDescription(produit.getDescription());
        dto.setCategorie(produit.getCategorie());
        dto.setPrix(produit.getPrix());
        dto.setQuantite(produit.getQuantite());
        dto.setQuantiteMinimale(produit.getQuantiteMinimale());
        dto.setFournisseur(produit.getFournisseur());
        return dto;
    }
}
