package sports.football.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.utility.SportsUtilities;
import sports.utility.json.CompetitionWriter;
import sports.utility.web.WebPage;

@Component
public class CompetitionParser {

	@Autowired
	private JsoupParser parser;
	@Autowired
	private SportsUtilities sportsUtilities;
	@Autowired
	private CompetitionWriter competitionWriter;
	private Map<String, List<String>> competitionDetails;

	public CompetitionParser() {
	}

	@PostConstruct
	private void init() {
		competitionDetails = new HashMap<>();
	}

	public void parseCompetitions(List<WebPage> webpages) {
		webpages.forEach(webpage -> {
			competitionWriter.addAssociation(webpage.getKey());
			processWebDocuments(webpage.getKey(), webpage);
		});
	}

	private void processWebDocuments(String associationName, WebPage webpage) {
		webpage.getWebDocs().stream().forEach(doc -> {
			parser.setDocument(doc);
			String competitionName = setCompetitionName(doc);
			if (competitionName != null) {
				String teamsUrl = sportsUtilities.worldFootballUrl() + getHref(doc, "teams");
				String fixturesUrl = sportsUtilities.worldFootballUrl() + getHref(doc, "schedule");
				competitionWriter.addTeamsFixturesUrls(associationName, competitionName, teamsUrl, fixturesUrl);
				addMapItem(associationName, competitionName);
			}
		});
	}

	private void addMapItem(String associationName, String competitionName) {
		competitionName = sportsUtilities.getPreferredName(competitionName);
		if (competitionDetails.get(associationName) == null) {
			List<String> list = new ArrayList<>();
			list.add(competitionName);
			competitionDetails.put(associationName, list);
		} else {
			competitionDetails.get(associationName).add(competitionName);
		}
	}

	private String getHref(Document doc, String query) {
		return doc.select("#navi > div.sitenavi > div.navibox2 > div > ul > li > a").stream()
				.filter(element -> element.text().equalsIgnoreCase(query)).map(tag -> {
					String name = tag.attr("href");
					return name;
				}).findFirst().get().trim();
	}

	private String setCompetitionName(Document doc) {
		return parser.getElementsByTag("meta").stream().filter(tag -> tag.attr("name").equalsIgnoreCase("page-topic"))
				.limit(1).map(tag -> {
					String name = tag.attr("content");
					return name;
				}).findFirst().get().trim();
	}

	public void writeJsonFiles() {
		sportsUtilities.writeJsonFile(sportsUtilities.getTeamsUrlPath(), competitionWriter.getTeamsObject());
		sportsUtilities.writeJsonFile(sportsUtilities.getFixturesUrlPath(), competitionWriter.getFixturesObject());
	}

	public Map<String, List<String>> getCompetitionDetails() {
		return competitionDetails;
	}
}
