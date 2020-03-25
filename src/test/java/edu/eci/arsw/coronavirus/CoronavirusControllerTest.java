package edu.eci.arsw.coronavirus;

import edu.eci.arsw.coronavirus.controller.CoronavirusController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CoronavirusController.class)
public class CoronavirusControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldGetCovidByName() throws Exception {
        mvc.perform(get("/coronavirus/Canada")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldNotGetCovidByName() throws Exception {
        mvc.perform(get("/coronavirus/notfound")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
