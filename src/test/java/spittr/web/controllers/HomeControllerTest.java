package spittr.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;

/**
 * Created by evgenypavlenko on 10/7/17.
 */
public class HomeControllerTest {

    private HomeController homeController;

    @Before
    public void setUp() {
        homeController = new HomeController();
    }

    @Test
    public void testHomePage() throws Exception {
        assertEquals("home", homeController.home());
    }

    @Test
    public void testHomePageMock() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("home"));
    }

}

