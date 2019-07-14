package sports.football.parser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.football.entity.Association;
import sports.football.entity.Competition;
import sports.football.entity.CompetitionTeam;
import sports.football.entity.Team;
import sports.football.entity.manager.FootballEntityManager;
import sports.utility.SportsUtilities;
import sports.utility.web.UltilityWebPage;

@Component
public class TeamParser {

	@Autowired
	private SportsUtilities sportsUtilities;
	@Autowired
	private JsoupParser jsoupParser;
	@Autowired
	private FootballEntityManager fem;

	public TeamParser() {
	}

	public void parseTeams(List<UltilityWebPage> webpages) {
		webpages.forEach(teamPage -> {
			teamPage.getDocs().forEach(doc -> {
				jsoupParser.setDocument(doc);
				List<Team> teams = setTeams();
				setLeague(teamPage.getAssociationName(), teamPage.getCompetitionName(), teams);
			});
		});
	}

	private List<Team> setTeams() {
		List<Team> teams = new ArrayList<>();
		jsoupParser.getElementsByCss("div.box > div > table > tbody > tr").forEach(element -> {
			String name = sportsUtilities.getPreferredName(element.select("td:nth-child(2)").text().trim());
			String associationName = element.select("td:nth-child(3) > img").get(0).attr("title").trim();
			Association association = fem.getAssociationByName(associationName);
			if (association != null) {
				Team team = new Team(name, association);
				fem.saveTeam(team);
				teams.add(fem.getTeamByNameAndAssociation(team.getName(), team.getAssociation()));
			}
		});
		return teams;
	}

	private void setLeague(String associationName, String competitionName, List<Team> teams) {
		Association association = fem.getAssociationByName(associationName);
		Competition competition = fem.getCompetitionByNameAndAssociation(competitionName, association);
		String seasonId = sportsUtilities.getSeasonId(jsoupParser);
		if (seasonId != null && competition != null) {
			teams.forEach(team -> fem.saveCompetitionTeam(new CompetitionTeam(team, competition, seasonId)));
		}
	}
}
