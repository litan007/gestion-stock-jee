package com.gestionstock.validation;

import java.util.regex.Pattern;

/**
 * Utilitaire de validations métier complémentaires.
 */
public final class Validation {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private Validation() {
    }

    public static boolean estEmailValide(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean estTexteRenseigne(String texte) {
        return texte != null && !texte.trim().isEmpty();
    }
}
