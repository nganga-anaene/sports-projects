package sports.football.entity.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.football.entity.Association;
import sports.football.entity.Competition;
import sports.football.entity.CompetitionTeam;
import sports.football.entity.Confederation;
import sports.football.entity.Fixture;
import sports.football.entity.Result;
import sports.football.entity.Team;
import sports.football.entity.repository.AssociationRepository;
import sports.football.entity.repository.CompetitonRepository;
import sports.football.entity.repository.ConfederationRepository;
import sports.football.entity.repository.FixtureRepository;
import sports.football.entity.repository.ResultRepository;
import sports.football.entity.repository.CompetitionTeamRepository;
import sports.football.entity.repository.TeamRepositiry;

@Component
public class FootballEntityManager {

	@Autowired
	private ConfederationRepository confederationRepository;
	@Autowired
	private AssociationRepository associationRepository;
	@Autowired
	private CompetitonRepository competitonRepository;
	@Autowired
	private TeamRepositiry teamRepository;
	@Autowired
	private CompetitionTeamRepository competitionTeamRepository;
	@Autowired
	private FixtureRepository fixtureRepository;
	@Autowired
	private ResultRepository resultRepository;

	public FootballEntityManager() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Confederation repository operations start
	 */
	public void deleteAllConfederations() {
		confederationRepository.deleteAll();
	}

	public void saveConfederation(Confederation confederation) {
		if (confederationRepository.findConfederationByNameAndRegionNameAndAbb(confederation.getName(),
				confederation.getRegionName(), confederation.getAbb()) == null) {
			confederationRepository.save(confederation);
		}
	}

	public Confederation getConfederationByAbbreviation(String abb) {
		return confederationRepository.findConfederationByAbb(abb);
	}

	public List<Confederation> getAllConfederations() {
		List<Confederation> temp = new ArrayList<>();
		confederationRepository.findAll().forEach(temp::add);
		return temp;
	}

	/* End of confederation operations */

	/*
	 * Start of Association operations
	 */

	public void saveAssociation(Association association) {
		if (associationRepository.findAssociationByNameAndAssociationAbbAndConfederation(association.getName(),
				association.getAssociationAbb(), association.getConfederation()) == null) {
			associationRepository.save(association);
		}
	}

	public List<Association> getAllAssociations() {
		List<Association> temp = new ArrayList<>();
		associationRepository.findAll().forEach(temp::add);
		return temp;
	}

	public Association getAssociationByName(String name) {
		return associationRepository.findAssociationByName(name);
	}

	/* End of Association operations */

	/*
	 * Start of Competition operations
	 */
	public void saveCompetition(Competition competition) {
		if (competitonRepository.findCompetitionByNameAndAssociation(competition.getName(),
				competition.getAssociation()) == null) {
			competitonRepository.save(competition);
		}
	}

	public Competition getCompetitionByNameAndAssociation(String name, Association association) {
		return competitonRepository.findCompetitionByNameAndAssociation(name, association);
	}

	public Competition getCompetitionByName(String name) {
		return competitonRepository.findCompetitionByName(name);
	}

	public List<Competition> getAllCompetitions() {
		List<Competition> temp = new ArrayList<>();
		competitonRepository.findAll().forEach(temp::add);
		return temp;
	}

	public void deleteAllCompetitions() {
		competitonRepository.deleteAll();
	}

	/* End of Competition operations */
	/*
	 * Start of Team operations
	 */
	public List<Team> getAllTeams() {
		List<Team> temp = new ArrayList<>();
		teamRepository.findAll().forEach(temp::add);
		return temp;
	}

	public void deleteAllteams() {
		teamRepository.deleteAll();
	}

	public Team getTeamByNameAndAssociation(String name, Association association) {
		return teamRepository.findTeamByNameAndAssociation(name, association);
	}

	public List<Team> getTeamByName(String name) {
		return teamRepository.findTeamByName(name);
	}

	public List<Team> getTeamByAssociation(Association association) {
		return teamRepository.findTeamsByAssociation(association);
	}

	public void saveTeam(Team team) {
		if (teamRepository.findTeamByNameAndAssociation(team.getName(), team.getAssociation()) == null) {
			teamRepository.save(team);
		}
	}
	/* End of Team operations */

	/*
	 * Start of League operations
	 */

	public void saveCompetitionTeam(CompetitionTeam competitionTeam) {
		if (competitionTeamRepository.findCompetitionTeamByCompetitionAndTeamAndSeasonId(
				competitionTeam.getCompetition(), competitionTeam.getTeam(), competitionTeam.getSeasonId()) == null) {
			competitionTeamRepository.save(competitionTeam);
		}
	}

	public CompetitionTeam getCompetitionTeamByCompetitionAndTeamAndSeasonId(Competition competition, Team team,
			String seasonId) {
		return competitionTeamRepository.findCompetitionTeamByCompetitionAndTeamAndSeasonId(competition, team,
				seasonId);
	}

	public List<CompetitionTeam> getCompetitionTeamsByCompetition(Competition competition) {
		List<CompetitionTeam> temp = new ArrayList<>();
		competitionTeamRepository.findCompetitionTeamsByCompetition(competition).forEach(temp::add);
		return temp;
	}

	public List<CompetitionTeam> getAllCompetionTeams() {
		List<CompetitionTeam> temp = new ArrayList<>();
		competitionTeamRepository.findAll().forEach(temp::add);
		return temp;
	}

	public List<CompetitionTeam> getCompetitionTeamsByTeam(Team team) {
		List<CompetitionTeam> temp = new ArrayList<>();
		competitionTeamRepository.findCompetitionTeamsByTeam(team).forEach(temp::add);
		return temp;
	}

	public List<CompetitionTeam> getCompetitionTeamsByCompetitionAndSeasonId(Competition competition, String seasonId) {
		return competitionTeamRepository.findCompetitionTeamsByCompetitionAndSeasonId(competition, seasonId);
	}

	public void deleteAllCompetitionTeams() {
		competitionTeamRepository.deleteAll();
	}

	/* end of League operations */

	/*
	 * start of Fixture operations
	 */
	public Fixture getFixtureByHomeTeamAwayTeamAndMatchDay(CompetitionTeam homeTeam, CompetitionTeam awayTeam,
			Calendar matchDay) {
		return fixtureRepository.findByCompetitionTeam1AndCompetitionTeam2AndMatchDay(homeTeam, awayTeam,
				matchDay);
	}

	public void saveFixture(Fixture fixture) {
		if (fixtureRepository.findByCompetitionTeam1AndCompetitionTeam2AndMatchDay(fixture.getCompetitionTeam1(),
				fixture.getCompetitionTeam2(), fixture.getMatchDay()) == null) {
			fixtureRepository.save(fixture);
		}
	}

	public void deleteAllFixtures() {
		resultRepository.deleteAll();
	}

	/* End of fixture operations */

	/*
	 * Start of Result Operations
	 */
	public void saveResult(Result result) {
		if (resultRepository.findResultByFixture(result.getFixture()) == null) {
			resultRepository.save(result);
		}
	}

	public void deleteAllResults() {
		resultRepository.deleteAll();
	}
}
