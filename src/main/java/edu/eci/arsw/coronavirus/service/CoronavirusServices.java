package edu.eci.arsw.coronavirus.service;

import com.mashape.unirest.http.JsonNode;

import edu.eci.arsw.coronavirus.cache.CoronavirusCache;
import edu.eci.arsw.coronavirus.connection.HttpConnectionService;
import edu.eci.arsw.coronavirus.controller.CoronavirusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("airportFinder")
public class CoronavirusServices {
    @Autowired
    HttpConnectionService http;

    @Autowired
    CoronavirusCache cache;

    public JsonNode coronavirusByName(String name) throws CoronavirusException {
        JsonNode res = cache.getByName(name);
        if(res != null){
            return res;
        }else{
            JsonNode response = http.coronavirtusByName(name);
            cache.addByName(name,response);
            return response;
        }
    }

    public JsonNode allCoronavirus() throws CoronavirusException {
        JsonNode res =  cache.getAll();
        if(res != null){
            return res;
        }else{
            JsonNode response = http.getAll();
            cache.addCoronavirusAll(response);
            res =  cache.getAll();
            return res;
        }
    }

    public JsonNode getCoordenadasByName(String name) throws CoronavirusException {
        JsonNode response = http.getCoords(name);
        return response;
    }

}