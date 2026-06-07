package com.gestionstock.controller;

import com.gestionstock.service.ProduitService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Contrôleur du tableau de bord.
 */
@Controller
public class HomeController {

    private final ProduitService produitService;

    public HomeController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("totalProduits", produitService.countDisponibles());
        model.addAttribute("ruptures", produitService.countRupture());
        model.addAttribute("stocksFaibles", produitService.countStockFaible());
        return "dashboard";
    }
}
