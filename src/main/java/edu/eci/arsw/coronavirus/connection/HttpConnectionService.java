package edu.eci.arsw.coronavirus.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.coronavirus.controller.CoronavirtusException;
import org.springframework.stereotype.Service;

@Service("connection")
public class HttpConnectionService {

    public JsonNode coronavirtusByName(String name) throws CoronavirtusException {
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=" + name)
                    .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                    .header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8")
                    .asJson();
            if (response.getBody().getArray().length() == 0) {
                throw new CoronavirtusException(CoronavirtusException.NOT_FOUND);
            }
            System.out.println("paso el request");
            return response.getBody();
        }catch (UnirestException e){
            throw new CoronavirtusException(CoronavirtusException.CONNECTION_FAILED);
        }
    }

    public JsonNode getAll() throws CoronavirtusException {
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
                    .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                    .header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8")
                    .asJson();
            return response.getBody();
        }catch (UnirestException e){
            throw new CoronavirtusException(CoronavirtusException.CONNECTION_FAILED);
        }
    }
}