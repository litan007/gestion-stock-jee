package com.gestionstock.security;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityLoginIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAuthenticateAdminUser() throws Exception {
        mockMvc.perform(formLogin("/login").user("admin").password("admin123"))
            .andExpect(authenticated().withUsername("admin"))
            .andExpect(redirectedUrl("/dashboard"));
    }
}
