/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lee
 */
@Component
public class JsonReader {
    private JSONParser parser;
    private JSONObject object;
    private Map<String, Object> map;

    public JsonReader() {
    }
    
    public void openJsonFile(String file) {
        try(FileReader r = new FileReader(file)){
            parser = new JSONParser();
            object = (JSONObject) parser.parse(r);
            setJsonObjects();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setJsonObjects() {
        map = new HashMap();
        object.keySet().forEach(key ->{
            map.put(key.toString(), object.get(key));
        });
    }
    
    public Map<String, Object> getJsonObjects(JSONObject objects) {
        Map tempMap = new HashMap();
        objects.keySet().forEach(key -> {
            tempMap.put(key.toString(), objects.get(key));
        });
        return tempMap;
    } 

    public Map<String, Object> getObjectMap() {
        return map;
    }
    
    public Stream<Map.Entry<String, Object>> getObjectMapStream() {
        return map.entrySet().stream();
    }
    
    public List<JSONObject> getJsonArray(String key) {
        return (List<JSONObject>) map.get(key);
    }
    
    
    public JSONObject getJsonObject(String key) {
        return (JSONObject) object.get(key);
    }
}
