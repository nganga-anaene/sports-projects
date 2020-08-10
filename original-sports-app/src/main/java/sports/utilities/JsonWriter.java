/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lee
 */
@Component
public class JsonWriter {

    public JsonWriter() {
    }

    public void writeJsonObject(String file, JSONObject object) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(object.toJSONString());
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(JsonWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
