package com.gestionstock.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidationTest {

    @Test
    void shouldValidateEmailFormat() {
        assertTrue(Validation.estEmailValide("user@test.com"));
        assertFalse(Validation.estEmailValide("user-test.com"));
    }
}
