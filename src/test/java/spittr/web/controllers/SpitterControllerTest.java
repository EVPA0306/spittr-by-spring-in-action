package spittr.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import spittr.data.Spitter;
import spittr.web.repositories.SpitterRepository;

import java.io.File;

/**
 * Created by evgenypavlenko on 10/7/17.
 */
public class SpitterControllerTest {

    private SpitterController spitterController = new SpitterController();

    @Test
    public void testRegistrationForm() {
        assertEquals("registerForm", spitterController.showRegistrationForm());
    }

    @Test
    public void shouldShowRegistration() throws Exception {
        MockMvc mockMvc = standaloneSetup(spitterController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/spitter/register"))
                .andExpect(view().name("registerForm"));
    }

    @Test
    public void shoudProcessRegistration() throws Exception {

        SpitterRepository spitterRepository = mock(SpitterRepository.class);
        Spitter unsaved = new Spitter("JBauer","24hours","Jack", "Bauer");
        Spitter saved = new Spitter(24L,"JBauer","24hours","Jack", "Bauer");

        when(spitterRepository.save(unsaved)).thenReturn(saved);

        SpitterController spitterController = new SpitterController(spitterRepository);
        MockMvc mockMvc = standaloneSetup(spitterController).build();

        /*mockMvc.perform(post("/spitter/register")
                .param("firstName", "Jack")
                .param("lastName", "Bauer")
                .param("userName", "JBauer")
                .param("password", "24hours")
                .param("profilePicture", "/f553bdc8-e12e-4b41-9996-564fac518d1c.jpg"))
                .andExpect(redirectedUrl("/spitter/JBauer"));
        */
        //verify(spitterRepository, atLeastOnce()).save(unsaved);
    }
}
