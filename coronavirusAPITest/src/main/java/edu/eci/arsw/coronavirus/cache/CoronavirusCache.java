package edu.eci.arsw.coronavirus.cache;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import com.mashape.unirest.http.JsonNode;

@Service
public class CoronavirusCache {
    private ConcurrentHashMap<String, JsonNode> countries = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, JsonNode> provincies = new ConcurrentHashMap<>();

    public JsonNode getByName(String name){
        if(countries.containsKey(name)){
            return countries.get(name);
        }else{
            return null;
        }
    }
    public JsonNode getAll(){
        String s = "[";
        for (ConcurrentHashMap.Entry<String, JsonNode> entry : countries.entrySet()) {
            s += entry.getValue()+",";
        }
        s+="{}]";
        if(s.length() > 4){
            JsonNode jsonNode = new JsonNode(s);
            return jsonNode;
        }else{
            return null;
        }
    }

    public void addCoronavirusAll( JsonNode data){
        JSONArray jsonArray = data.getObject().getJSONObject("data").getJSONArray("covid19Stats");
        for(int i = 0; i < jsonArray.length() ; i++){
            try {
                JSONObject json = jsonArray.getJSONObject(i);
                if (!countries.containsKey(json.get("country").toString())){
                    JSONObject js = new JSONObject();
                    js.put("country",json.get("country"));
                    js.put("deaths",json.get("deaths"));
                    js.put("confirmed",json.get("confirmed"));
                    JsonNode jn = new JsonNode(js.toString());
                    countries.put(json.get("country").toString(),jn);
                }else{
                    JsonNode actual = countries.get(json.get("country").toString());
                    JSONObject js =  actual.getObject();
                    int suma = js.getInt("deaths") +json.getInt("deaths");
                    js.remove("deaths");
                    js.put("deaths",suma);
                    suma = js.getInt("confirmed")+json.getInt("confirmed");
                    js.remove("confirmed");
                    js.put("confirmed",suma);
                }
                JsonNode jn = new JsonNode(json.toString());
                provincies.put(json.get("province").toString(),jn);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}