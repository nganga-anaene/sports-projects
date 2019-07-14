package sports.football.entity.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.football.collector.UtilityCollector;
import sports.football.parser.FixtureParser;
import sports.utility.SportsUtilities;

@Component
public class FixtureManager {

	@Autowired
	private UtilityCollector fixtureCollector;
	@Autowired
	private SportsUtilities sportsUtilities;	
	@Autowired
	private FixtureParser fixtureParser;

	public FixtureManager() {
	}

	public void processFixtures() {
		fixtureCollector.readUrls(sportsUtilities.getFixturesUrlPath());
		fixtureParser.parseFixtures(fixtureCollector.getUtilityWebPages());
	}
}
