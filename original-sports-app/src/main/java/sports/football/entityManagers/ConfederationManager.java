/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.entityManagers;

import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sports.football.entities.Confederations;
import sports.utilities.SportsUtilities;

/**
 *
 * @author Lee
 */
@Component
public class ConfederationManager {

    @Autowired
    private FootballEntitiesManager fem;
    @Autowired
    private SportsUtilities sportsUtilities;
    private final String CONFEDERATIONS = "confederations";

    public ConfederationManager() {
    }

    public void saveConfederations() {
        sportsUtilities.readJsonFile(CONFEDERATIONS);
        sportsUtilities.getJsonReader()
                .getJsonArray(CONFEDERATIONS)
                .forEach(object -> {
                    saveConfederation(object);
                });
    }

    private void saveConfederation(JSONObject object) {
        try {
            Confederations con = new Confederations(object.get("name").toString(),
                    object.get("regionName").toString(),
                    object.get("abb").toString());
            fem.saveConfederation(con);
        } catch (Exception e) {
            System.out.println("Error inserting confederation: " + e.getMessage());
        }
    }

    public List<Confederations> getAllConfederations() {
        return fem.getAllConfederations();
    }

    public void testConfederationsRepository() {
        System.out.println(fem.getConfederationsByName("Union of European Football Associations"));
        System.out.println(fem.getConfederationsByAbb("OFC"));
    }
}
