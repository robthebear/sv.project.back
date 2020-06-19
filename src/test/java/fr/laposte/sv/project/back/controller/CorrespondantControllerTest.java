package fr.laposte.sv.project.back.controller;

import fr.laposte.sv.project.back.model.Correspondant;
import fr.laposte.sv.project.back.repository.CorrespondantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CorrespondantControllerTest {

    @MockBean
    private CorrespondantRepository correspondantRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void findAll() throws Exception {
        when(correspondantRepository.findAll()).thenReturn(new ArrayList<>());

        ResultActions reponse = this.mockMvc.perform(get("/correspondant"));

        reponse.andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        when(this.correspondantRepository.findById("bgf123")).thenReturn(null);

        ResultActions reponse = this.mockMvc.perform(get("/correspondant/id/"));

        reponse.andExpect(status().isOk());

    }

    @Test
    void findByNom() {
        fail();

    }

    @Test
    void findByFonction() {
        fail();

    }

    @Test
    void findByEmail() {
        fail();

    }

    @Test
    void delCorrespondant() {
        fail();

    }

    @Test
    void updateCorrespondant() {
        fail();

    }
}
