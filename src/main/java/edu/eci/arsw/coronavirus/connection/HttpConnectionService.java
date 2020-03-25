package edu.eci.arsw.coronavirus.connection;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.coronavirus.controller.CoronavirusException;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service("connection")
public class HttpConnectionService {

    public JsonNode coronavirtusByName(String name) throws CoronavirusException {
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=" + name)
                    .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                    .header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8")
                    .asJson();
            if (!response.getBody().getObject().get("message").toString().contains("OK")) {
                throw new CoronavirusException(CoronavirusException.NOT_FOUND);
            }
            return response.getBody();
        }catch (UnirestException e){
            throw new CoronavirusException(CoronavirusException.CONNECTION_FAILED);
        }
    }

    public JsonNode getAll() throws CoronavirusException {
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
                    .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                    .header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8")
                    .asJson();
            return response.getBody();
        }catch (UnirestException e){
            throw new CoronavirusException(CoronavirusException.CONNECTION_FAILED);
        }
    }

    public JsonNode getCoords(String name) throws CoronavirusException {
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://restcountries-v1.p.rapidapi.com/name/"+name)
                    .header("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
                    .header("x-rapidapi-key", "84479b851cmsh5442c7b5ae08871p19b505jsn229bda6e32bd")
                    .asJson();
            return response.getBody();
        }catch (UnirestException e){
            throw new CoronavirusException(CoronavirusException.CONNECTION_FAILED);
        }
    }
}