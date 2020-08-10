/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.entityManagers;

import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sports.football.entities.Associations;
import sports.football.entities.Competition;
import sports.football.entities.Confederations;
import sports.football.entities.Team;
import sports.football.repositories.AssociationsRepository;
import sports.football.repositories.CompetitionRepository;
import sports.football.repositories.ConfederationsRepository;
import sports.football.repositories.TeamRepository;

/**
 *
 * @author Lee
 */
@Component
public class FootballEntitiesManager {

    @Autowired
    private AssociationsRepository associationsRepository;
    @Autowired
    private ConfederationsRepository confederationsRepository;
    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private TeamRepository teamRepository;

    public FootballEntitiesManager() {
    }

    public String getConfederationNameFromAssociation(String key) {
        return confederationsRepository.findById(
                associationsRepository.findAssociationsByName(key)
                        .getConfederationId()
                        .getId())
                .get()
                .getName();
    }

    public void deleteAllEntityData() {
        teamRepository.deleteAll();
        competitionRepository.deleteAll();
        associationsRepository.deleteAll();
        confederationsRepository.deleteAll();
    }

    /*start of confederations operations
     */
    public Confederations getConfederationsByAbb(String key) {
        return confederationsRepository.findConfederationsByAbb(key);
    }

    public Confederations getConfederationsByName(String key) {
        return confederationsRepository.findConfederationsByName(key);
    }

    public void saveConfederation(Confederations confederations) {
        try {
            confederationsRepository.save(confederations);
        } catch (EntityExistsException e) {
        } catch (Exception e) {
            System.out.println(String.format(saveErrorMessage(), confederations, e.getMessage()));
        }
    }

    public List<Confederations> getAllConfederations() {
        return confederationsRepository.findAll();
    }

    /*end of confederations operations
     */

 /*start of associations operations
     */
    public void saveAssociation(Associations associations) {
        try {
            associationsRepository.save(associations);
        } catch (EntityExistsException e) {
        } catch (Exception e) {
            System.out.println(String.format(saveErrorMessage(), associations, e.getMessage()));
        }
    }

    public Associations getAssociationByName(String name) {
        return associationsRepository.findAssociationsByName(name);
    }

    public List<Associations> getAllAssociations() {
        return (List) Arrays.asList(associationsRepository.findAll());
    }

    /*end of associations operations
     */
 /*start of competiton operations
     */
    public void saveCompetiton(Competition competition) {
        try {
            competitionRepository.save(competition);
        } catch (EntityExistsException e) {
        } catch (Exception e) {
            System.out.println(String.format(saveErrorMessage(), competition, e.getMessage()));
        }
    }

    public Competition getCompetitionByName(String name) {
        return competitionRepository.findCompetitionByName(name);
    }

    public List<Competition> getAllCompetitions() {
        return (List) competitionRepository.findAll();
    }

    /*end of competition operations
     */
 /*start of teams operations
     */
    public void saveTeam(Team team) {
        try {
            teamRepository.save(team);
        } catch (EntityExistsException e) {
        } catch (Exception e) {
            System.out.println(String.format(saveErrorMessage(), team, e.getMessage()));
        }
    }

    public List<Team> getAllTeams() {
        return (List) teamRepository.findAll();
    }

    public Team getTeamById(int id) {
        return teamRepository.findById(id).get();
    }

    public Team getTeamByAssociationAndName(String assocationName, String name) {
        Associations a = associationsRepository.findAssociationsByName(assocationName);
        return teamRepository.findTeamByAssociationId(a)
                .stream().filter(team -> team.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public Team TestTeamAssociationName(Associations associationId, String name) {
        return teamRepository.findTeamByAssociationIdAndName(associationId, name);
    }

    public void deleteAllTeam() {
        teamRepository.deleteAll();
    }

    /*end of teams operations
     */
 /*start of leagues operations
     */
 /*end of leagues operations
     */
 /*start of fixtures operations
     */
 /*end of fixtures operations
     */
 /*start of results operations
     */
 /*end of results operations
     */
 /*start of predictions operations
     */
 /*end of predictions operations
     */
    private String saveErrorMessage() {
        return "could not save: %s %s";
    }
}
