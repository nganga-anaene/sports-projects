package sports.football.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.utility.SportsUtilities;
import sports.utility.web.WebPage;

@Component
public class CompetitionCollector {

	@Autowired
	private SportsUtilities sportsUtilities;
	private List<WebPage> webpages;

	public CompetitionCollector() {
	}

	public void readCompetitionUrls() {
		sportsUtilities.readJsonFile("src/main/resources/json/associations.json");
		processCompetitionUrls();
	}

	private void processCompetitionUrls() {
		setWebPages();
		webpages.forEach(webpage -> {
			webpage.getUrls().forEach(url -> {
				Document doc = sportsUtilities.getWebDocument(url);
				if (doc != null) {
					webpage.addWebDoc(doc);
				}
			});
		});
	}

	private void setWebPages() {
		webpages = sportsUtilities.getJsonFileReader().getObjectsMap().entrySet().stream().map(entry -> {
			String key = entry.getKey();
			List<String> urls = getCompetitionUrls(key);
			return new WebPage(key, urls);
		}).collect(Collectors.toList());
	}

	private List<String> getCompetitionUrls(String key) {
		List<String> list = new ArrayList<>();
		for (Object o : sportsUtilities.getJsonFileReader().getMapJsonArray(key)) {
			list.add(String.valueOf(o));
		}
		return list;
	}

	public List<WebPage> getWebpages() {
		return webpages;
	}

}
