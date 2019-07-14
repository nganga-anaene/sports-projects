package sports.football.entity.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.football.collector.UtilityCollector;
import sports.football.parser.TeamParser;
import sports.utility.SportsUtilities;

@Component
public class TeamManager {

	@Autowired
	private UtilityCollector teamCollector;
	@Autowired
	private TeamParser teamParser;
	@Autowired
	private SportsUtilities sportsUtilities;

	public TeamManager() {
	}

	public void processTeams() {
		teamCollector.readUrls(sportsUtilities.getTeamsUrlPath());
		teamParser.parseTeams(teamCollector.getUtilityWebPages());
	}
}
