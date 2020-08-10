/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.entityManagers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sports.football.entities.Associations;
import sports.football.entities.Competition;
import sports.football.web.CompetitionCollector;
import sports.utilities.CompetitionDetails;
import sports.utilities.JsonObjects;
import sports.utilities.SportsUtilities;

/**
 *
 * @author Lee
 */
@Component
public class CompetitionManager {

    @Autowired
    private CompetitionCollector collector;
    @Autowired
    private FootballEntitiesManager fem;
    @Autowired
    private SportsUtilities sportsUtilities;
    private JsonObjects jsonObjects;
    private final String teams = "Teams";
    private final String fixtures = "Fixtures";
    private final String leagues = "Leagues";
    private final String competitionsUrl = "competition-urls-json";

    public CompetitionManager() {
    }

    // TODO change this later
    private void setCompetitionDetails() {
        collector.init();
        collector.setJsoupCompetitionDetails();
        //collector.setSeleniumCompetitionDetails();
    }

    public void saveCompetitionsDetails() {
        setCompetitionDetails();
        processCompetitionDetails();
    }

    private void processCompetitionDetails() {
        initJsonObjects();
        collector.getCompetitonDetailsStream()
                .forEach(compDetails -> {
                    saveCompetitions(getCompetition(compDetails));
                    setJsonUrls(compDetails);
                });
        writeJsonUrls();
    }

    private Competition getCompetition(CompetitionDetails compDetails) {
        Associations associationId = fem.getAssociationByName(compDetails.getAssociationName());
        String competitionName = sportsUtilities.swapIntegerPostion(compDetails.getCompetitonName());
        return new Competition(competitionName, associationId);
    }

    private void saveCompetitions(Competition competition) {
        try {
            fem.saveCompetiton(competition);
        } catch (Exception e) {
            System.out.println("Error saving competition: " + competition + "\n" + e.getMessage());
        }
    }

    private void setJsonUrls(CompetitionDetails compDetails) {
        setJsonUrlObject(jsonObjects.getJsonObject(teams), compDetails.getAssociationName(), compDetails.getTeamsUrl());
        setJsonUrlObject(jsonObjects.getJsonObject(fixtures), compDetails.getAssociationName(), compDetails.getFixturesUrl());
        setJsonUrlObject(jsonObjects.getJsonObject(leagues), compDetails.getAssociationName(), compDetails.getLeaguesUrl());
    }

    private void writeJsonUrls() {
        String path = sportsUtilities.getPropertyReader()
                .getStringProperty(competitionsUrl)
                .orElse("");
        sportsUtilities.getJsonWriter()
                .writeJsonObject(path, jsonObjects.getObject());

    }

    private void initJsonObjects() {
        jsonObjects = new JsonObjects();
        jsonObjects.addJsonObject(teams, jsonObjects.getNewJsonObject());
        jsonObjects.addJsonObject(fixtures, jsonObjects.getNewJsonObject());
        jsonObjects.addJsonObject(leagues, jsonObjects.getNewJsonObject());
    }

    private void setJsonUrlObject(JSONObject jsonObject, String name, String url) {
        if (jsonObject.get(name) == null) {
            JSONArray array = jsonObjects.getNewJsonArray();
            array.add(url);
            jsonObject.put(name, array);
        } else {
            JSONArray array = (JSONArray) jsonObject.get(name);
            array.add(url);
        }
    }

}
