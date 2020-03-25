package edu.eci.arsw.coronavirus;

import com.mashape.unirest.http.JsonNode;
import edu.eci.arsw.coronavirus.connection.HttpConnectionService;
import edu.eci.arsw.coronavirus.controller.CoronavirusException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CovidConnectionTest {

    @Autowired
    HttpConnectionService http;

    @Test
    public void shouldGetCovidByName() throws CoronavirusException {
        JsonNode response = http.coronavirtusByName("London");
        assertNotNull(response);
        assertTrue(response.toString().contains("London"));
    }

    @Test
    public void shouldNotGetCovidByName() {
        try {
            JsonNode response = http.coronavirtusByName("notfound");
            fail("Debio fallar por consultar aeropuertos por un nombre inexistente");
        }catch (CoronavirusException e) {
            assertEquals(e.getMessage(),CoronavirusException.NOT_FOUND);
        }
    }
}