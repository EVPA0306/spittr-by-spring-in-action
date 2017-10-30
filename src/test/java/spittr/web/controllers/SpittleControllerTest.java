package spittr.web.controllers;

import org.junit.Ignore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;
import spittr.data.Spittle;
import spittr.web.repositories.SpittleRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
//import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Created by evgenypavlenko on 10/7/17.
 */

public class SpittleControllerTest {

    private SpittleRepository mockRepository;
    private SpittleController spittleController;

    @Before
    public void setUp() {
        mockRepository = Mockito.mock(SpittleRepository.class);
        spittleController = new SpittleController(mockRepository);
    }

    @Test
    public void shouldShowRecentPagedSpittles() throws Exception {

        List<Spittle> list = createSpittleList(50);

        when(mockRepository.findSpittles(238900L,50))
                .thenReturn(list);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(spittleController)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/spittles.jsp"))
                .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/spittles"))
                .andExpect(MockMvcResultMatchers.view().name("spittles"))
                .andExpect(model().attributeExists("spittleList"))
                //.andExpect(model().attribute("spittleList", hasItems( list.toArray() )))
        ;

    }

    @Test
    public void shouldShowPagedSpittles() throws Exception {

        List<Spittle> list = createSpittleList(50);

        when(mockRepository.findSpittles(238900L,50))
                .thenReturn(list);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(spittleController)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/spittles.jsp"))
                .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/spittles?max=238900&count=50"))
                .andExpect(MockMvcResultMatchers.view().name("spittles"))
                .andExpect(model().attributeExists("spittleList"))
                //.andExpect(model().attribute("spittleList", hasItems( list )))
        ;
    }

    @Test
    public void testSpittleFromPath() throws Exception {

        Spittle expediteSpittle = new Spittle(11L,"Spittle 11", LocalDateTime.now());

        when(mockRepository.findOne(11L)).thenReturn(expediteSpittle);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(spittleController)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/spittles/11"))
                .andExpect(MockMvcResultMatchers.view().name("spittle"))
                .andExpect(model().attributeExists("spittle"))
                .andExpect(model().attribute("spittle", expediteSpittle))
                ;
    }

    private List<Spittle> createSpittleList(int count) {
        List<Spittle> list = new ArrayList<>();
        for(int i=0; i<count; i++) {
            list.add(new Spittle(Long.valueOf(i), "Spttle " + i, LocalDateTime.now()));
        }
        return list;
    }

    private Spittle[] createSpittleArray(int count) {
        Spittle[] array = new Spittle[count];
        for(int i=0; i<count; i++) {
            array[i] = (new Spittle(Long.valueOf(i), "Spittle " + i, LocalDateTime.now()));
        }
        return array;
    }
}
