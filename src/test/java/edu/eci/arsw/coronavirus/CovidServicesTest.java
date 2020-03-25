package edu.eci.arsw.coronavirus;
import com.mashape.unirest.http.JsonNode;
import edu.eci.arsw.coronavirus.controller.CoronavirusException;
import edu.eci.arsw.coronavirus.service.CoronavirusServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CovidServicesTest {
    @Autowired
    CoronavirusServices service;

    @Test
    public void shouldGetCovidByName(){
        try{
            JsonNode respuesta = service.coronavirusByName("Colombia");
            assertNotNull(respuesta);
            assertTrue(respuesta.toString().contains("Colombia"));
        }catch (CoronavirusException e){
            fail("Deberia pasar");
        }
    }

    @Test
    public void shouldNotGetCovidByName(){
        try{
            JsonNode respuesta = service.coronavirusByName("notfound");
            fail("Deberia fallar");
        }catch (CoronavirusException e){
            assertEquals(e.getMessage(),CoronavirusException.NOT_FOUND);
        }
    }
}