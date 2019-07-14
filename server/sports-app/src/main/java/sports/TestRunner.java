package sports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sports.football.entity.manager.AssociationManager;
import sports.football.entity.manager.CompetitionManager;
import sports.football.entity.manager.ConfederationsManager;
import sports.football.entity.manager.FixtureManager;
import sports.football.entity.manager.TeamManager;

@Configuration
public class TestRunner {

	@Autowired
	private ConfederationsManager confederationsManager;
	@Autowired
	private AssociationManager associationManager;
	@Autowired
	private CompetitionManager competitionManager;
	@Autowired
	private TeamManager teamManager;
	@Autowired
	private FixtureManager fixtureManager;

	@Bean
	CommandLineRunner testFootballDatabase() {
		return args -> {
			//initConfeds();
//			initAssociations();
//			initCompetitions();
//			initTeams();
			initFixtures();
		};
	}

	public void initConfeds() {
		confederationsManager.readConfederations();
	}

	public void initAssociations() {
		associationManager.readAssociations();
	}

	public void initCompetitions() {
		competitionManager.processCompetitions();
	}

	public void initTeams() {
		teamManager.processTeams();
	}

	private void initFixtures() {
		fixtureManager.processFixtures();
	}
}
