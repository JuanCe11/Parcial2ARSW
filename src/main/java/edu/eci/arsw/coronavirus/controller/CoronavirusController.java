package edu.eci.arsw.coronavirus.controller;

import com.mashape.unirest.http.JsonNode;
import edu.eci.arsw.coronavirus.service.CoronavirusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/coronavirus")
public class CoronavirusController {
    @Autowired
    CoronavirusServices services = null ;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> GetAllCoronavirus(){
        try {
            return new ResponseEntity<>(services.allCoronavirus().toString(), HttpStatus.ACCEPTED);
        } catch (CoronavirusException e) {
            Logger.getLogger(CoronavirusController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path ="/{name}",method = RequestMethod.GET)
    public ResponseEntity<?> GetCoronavirusByName(@PathVariable("name")String name){
        try {
            JsonNode res = services.coronavirusByName(name);
            return new ResponseEntity<>(res.toString(), HttpStatus.ACCEPTED);
        } catch (CoronavirusException e) {
            Logger.getLogger(CoronavirusController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path ="/cords/{name}",method = RequestMethod.GET)
    public ResponseEntity<?> GetCordsByName(@PathVariable("name")String name){
        try {
            JsonNode res = services.getCoordenadasByName(name);
            return new ResponseEntity<>(res.toString(), HttpStatus.ACCEPTED);
        } catch (CoronavirusException e) {
            Logger.getLogger(CoronavirusController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
