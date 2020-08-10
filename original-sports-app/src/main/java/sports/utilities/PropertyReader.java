/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lee
 */
@Component
public class PropertyReader {

    private final String file = "src/main/resources/football/football.properties";
    private Properties properties;

    public PropertyReader() {
    }

    @PostConstruct
    private void loadFootballProperties() {
        try (InputStream in = new FileInputStream(file)) {
            properties = new Properties();
            properties.load(in);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Optional<String> getStringProperty(String key) {
        return Optional.of(String.valueOf(
                properties.get(key))
        );
    }

    public Optional<Integer> getIntegerProperty(String key) {
        return Optional.of(Integer.parseInt(
                properties.getProperty(key)
        ));
    }
}
