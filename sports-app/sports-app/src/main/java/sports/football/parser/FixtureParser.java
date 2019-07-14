package sports.football.parser;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

import sports.football.entity.Association;
import sports.football.entity.Competition;
import sports.football.entity.CompetitionTeam;
import sports.football.entity.Fixture;
import sports.football.entity.Result;
import sports.football.entity.Team;
import sports.football.entity.manager.FootballEntityManager;
import sports.utility.SportsUtilities;
import sports.utility.web.UltilityWebPage;

@Component
public class FixtureParser {
	@Autowired
	private JsoupParser jsoupParser;
	@Autowired
	private FootballEntityManager fem;
	@Autowired
	private SportsUtilities sportsUtilities;
	private String tableData = "td";

	public FixtureParser() {
	}
	
	@PostConstruct
	private void init() {
		fem.deleteAllFixtures();
		fem.deleteAllResults();
	}

	public void parseFixtures(List<UltilityWebPage> utilityWebPages) {
		utilityWebPages.forEach(page -> {
			setFixtures(page.getAssociationName(), page.getCompetitionName(), page.getDocs());
		});
	}

	private void setFixtures(String associationName, String competitionName, List<Document> docs) {
		Association association = fem.getAssociationByName(associationName);
		Competition competition = fem.getCompetitionByNameAndAssociation(competitionName, association);
		if (competition != null) {
			docs.forEach(doc -> {
				jsoupParser.setDocument(doc);
				String seasonId = sportsUtilities.getSeasonId(jsoupParser);
				if (seasonId != null) {
					setLeagueFixture(competition, seasonId);
				}
			});
		}
	}

	private void setLeagueFixture(Competition competition, String seasonId) {
		List<Element> table = jsoupParser
				.getElementsByCss("#site > div.white > div.content > div > div.box > div > table > tbody > tr");
		List<Element> rows = table.stream().filter(row -> row.getElementsByTag(tableData).size() == 8)
				.collect(Collectors.toList());
		sportsUtilities.setCurrentMatchDay(formatDate(getFirstMatchDay(rows)));
		List<CompetitionTeam> competitionTeams = fem.getCompetitionTeamsByCompetitionAndSeasonId(competition, seasonId);
		setFixture(processRows(rows), competitionTeams);
	}

	private List<FixtureHolder> processRows(List<Element> rows) {
		return rows.stream().map(row -> {
			List<Element> data = row.getElementsByTag(tableData);
			String homeTeamName = sportsUtilities.getPreferredName(data.get(2).text().trim());
			String awayTeamName = sportsUtilities.getPreferredName(data.get(4).text().trim());
			int[] goals = parseGoals(data.get(5).text());
			setMatchDay(Optional.of(data.get(0).text().trim()).or(""));
			return new FixtureHolder(homeTeamName, awayTeamName, goals, sportsUtilities.getCurrentMatchDay());
		}).collect(Collectors.toList());
	}

	private int[] parseGoals(String score) {
		String replaced = score.replaceAll("[^0-9]+", " ");
		String[] temp = replaced.split(" ");
		if (temp.length >= 2) {
			try {
				int[] goals = new int[2];
				goals[0] = Integer.parseInt(temp[0].trim());
				goals[1] = Integer.parseInt(temp[1].trim());
				return goals;
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	private int[] formatDate(String date) {
		String[] temp = date.split("/");
		try {
			int year = Integer.parseInt(temp[2]);
			int month = Integer.parseInt(temp[1]);
			int day = Integer.parseInt(temp[0]);
			return new int[] { year, month, day };
		} catch (NumberFormatException e) {
		}
		return null;
	}

	private String getFirstMatchDay(List<Element> rows) {
		return rows.stream().map(row -> {
			Element dateElement = row.getElementsByTag(tableData).get(0);
			return Optional.of(dateElement.text().trim()).or("");
		}).filter(date -> !date.isEmpty()).findFirst().get();
	}

	private void setMatchDay(String date) {
		if (!date.isEmpty()) {
			sportsUtilities.setCurrentMatchDay(formatDate(date));
		}
	}

	private void setFixture(List<FixtureHolder> fixtureList, List<CompetitionTeam> competitionTeams) {
		List<Team> teams = competitionTeams.stream().map(league -> league.getTeam()).collect(Collectors.toList());
		fixtureList.forEach(fixtureItem -> {
			Team homeTeam = getTeam(fixtureItem.getHomeTeamName(), teams);
			Team awayTeam = getTeam(fixtureItem.getAwayTeamName(), teams);
			if (homeTeam != null && awayTeam != null) {
				CompetitionTeam home = getCompetitionTeam(homeTeam, competitionTeams);
				CompetitionTeam away = getCompetitionTeam(awayTeam, competitionTeams);
				saveFixture(home, away, fixtureItem.getMatchday(), fixtureItem.getGoals());
			}
		});
	}

	private void saveFixture(CompetitionTeam home, CompetitionTeam away, Calendar date, int[] goals) {
		if (date != null) {
			fem.saveFixture(new Fixture(home, away, date));
			if (goals != null) {
				Fixture fixture = fem.getFixtureByHomeTeamAwayTeamAndMatchDay(home, away, date);
				saveResult(fixture, goals);
			}
		}
	}

	private void saveResult(Fixture fixture, int[] goals) {
		fem.saveResult(new Result(fixture, goals[0], goals[1]));
	}

	private CompetitionTeam getCompetitionTeam(Team team, List<CompetitionTeam> competitionTeams) {
		return competitionTeams.stream().filter(competition -> competition.getTeam().equals(team)).findFirst().get();
	}

	private Team getTeam(String name, List<Team> teams) {
		try {
			Team t = teams.parallelStream().filter(team -> team.getName().equalsIgnoreCase(name)).findFirst().get();
			return t;
		} catch (Exception e) {
		}
		return null;
	}

	private class FixtureHolder {
		private String homeTeamName;
		private String awayTeamName;
		private int[] goals;
		private Calendar matchday;

		public FixtureHolder(String homeTeamName, String awayTeamName, int[] goals, Calendar matchday) {
			this.homeTeamName = homeTeamName;
			this.awayTeamName = awayTeamName;
			this.goals = goals;
			this.matchday = matchday;
		}

		public String getHomeTeamName() {
			return homeTeamName;
		}

		public String getAwayTeamName() {
			return awayTeamName;
		}

		public int[] getGoals() {
			return goals;
		}

		public Calendar getMatchday() {
			return matchday;
		}
	}
}
