package sports.football.collector;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.football.parser.LeagueParser;
import sports.utility.SportsUtilities;
import sports.utility.web.UltilityWebPage;

@Component
public class UtilityCollector {

	@Autowired
	private SportsUtilities sportsUtilities;
	private List<UltilityWebPage> UtilityWebPages;
	@Autowired
	private LeagueParser leagueParser;

	public UtilityCollector() {
	}

	private void init(String path) {
		sportsUtilities.readJsonFile(path);
		UtilityWebPages = new ArrayList<>();
	}

	private void setUrls() {
		sportsUtilities.getJsonFileReader().getObjectsMap().entrySet().forEach(entry -> {
			sportsUtilities.getJsonFileReader().getInnerJsonObject(entry.getValue()).entrySet().forEach(innerEntry -> {
				UtilityWebPages.add(new UltilityWebPage(entry.getKey(), innerEntry.getKey(), innerEntry.getValue()));
			});
		});
	}

	public void readUrls(String path) {
		init(path);
		setUrls();
		UtilityWebPages.parallelStream().forEach(webPage -> {
			Document doc = sportsUtilities.getWebDocument(webPage.getTeamUrl());
			webPage.addDoc(doc);
			leagueParser.getLeagueUrls(doc).forEach(url -> webPage.addDoc(sportsUtilities.getWebDocument(url)));
		});
	}

	public List<UltilityWebPage> getUtilityWebPages() {
		return UtilityWebPages;
	}
}
