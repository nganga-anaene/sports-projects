/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Lee
 */
public class JsonObjects {

    private final JSONObject object;

    public JsonObjects() {
        object = new JSONObject();
    }
    
    public void addJsonArray(String key, JSONArray array) {
        object.put(key, array);
    }
    
    public void addJsonObject(String key, JSONObject jsono) {
        object.put(key, jsono);
    }
    
    public JSONObject getJsonObject(String key) {
        return (JSONObject)object.get(key);
    }
    
    public JSONArray getJsonArrayObject(String key) {
        return (JSONArray) object.get(key);
    }
    
    public JSONObject getObject() {
        return object;
    }
    
    public JSONObject getNewJsonObject() {
        return new JSONObject();
    }
    
    public JSONArray getNewJsonArray() {
        return new JSONArray();
    }
    
}
