/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sports.football.entityManagers.AssociationsManager;
import sports.football.entityManagers.CompetitionManager;
import sports.football.entityManagers.ConfederationManager;
import sports.football.entityManagers.FootballEntitiesManager;

/**
 *
 * @author Lee
 */
@Configuration
public class TestRunner {

    @Autowired
    private ConfederationManager confederationManager;
    @Autowired
    private FootballEntitiesManager fem;
    @Autowired
    private AssociationsManager associationsManager;
    @Autowired
    private CompetitionManager competitionManager;

    @Bean
    CommandLineRunner testDatabase() {
        return args -> {
            deleteEntityData();
            initConfederations();
            //initAssociations();
            //initCompetitions();
        };
    }

    private void initConfederations() {
        confederationManager.saveConfederations();
        //confederationManager.testConfederationsRepository();
    }

    private void initAssociations() {
        associationsManager.saveAssociations();
    }

    private void initCompetitions() {
        competitionManager.saveCompetitionsDetails();
    }

    private void deleteEntityData() {
        fem.deleteAllEntityData();
    }
}
