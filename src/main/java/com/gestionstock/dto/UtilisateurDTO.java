package com.gestionstock.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO utilisateur.
 */
@Getter
@Setter
public class UtilisateurDTO {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String motDePasse;

    @NotBlank
    private String nomComplet;

    @NotBlank
    private String role;
}
