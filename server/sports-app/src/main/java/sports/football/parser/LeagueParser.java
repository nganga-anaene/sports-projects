package sports.football.parser;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.utility.SportsUtilities;

@Component
public class LeagueParser {

	@Autowired
	private SportsUtilities sportsUtilities;
	@Autowired
	private JsoupParser jsoupParser;

	public LeagueParser() {
	}

	public List<String> getLeagueUrls(Document doc) {
		jsoupParser.setDocument(doc);
		return jsoupParser.getElementsByCss(sportsUtilities.getTeamSelectOptions()).stream().filter(element -> !element
				.attr(sportsUtilities.getSelectedAttribute()).equalsIgnoreCase(sportsUtilities.getSelectedAttribute()))
				.limit(20).map(element -> {
					String value = element.attr("value").trim();
					return sportsUtilities.worldFootballUrl() + value;
				}).collect(Collectors.toList());
	}
}
