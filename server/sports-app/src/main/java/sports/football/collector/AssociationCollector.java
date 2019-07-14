package sports.football.collector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.utility.SportsUtilities;
import sports.utility.web.Conditions;
import sports.utility.web.SeleniumBrowser;
import sports.utility.web.WebPage;

@Component
public class AssociationCollector {

	@Autowired
	private SportsUtilities sportsUtilities;
	@Autowired
	private SeleniumBrowser seleniumBrowser;
	@Autowired
	private Conditions conditions;
	private List<WebPage> pages;

	public AssociationCollector() {
	}

	public void readConfederationUrls() {
		sportsUtilities.readJsonFile("src/main/resources/json/confederations-urls.json");
		processConfederationUrls();
	}
	
	private void processConfederationUrls() {
		seleniumBrowser.initBrowser();
		seleniumBrowser.setPages(setWebPages());
		seleniumBrowser.processWebPage(conditions.pageHasId("mw-content-text"));
		seleniumBrowser.closeBrowser();
		pages = seleniumBrowser.getPages();
	}

	private List<WebPage> setWebPages() {
		return sportsUtilities.getJsonFileReader().getObjectsMap().entrySet().stream().map(entry -> {
			return new WebPage(entry.getKey(), Arrays.asList((String) entry.getValue()));
		}).collect(Collectors.toList());
	}
	
	public Map<String, Object> getUniqueAssociations() {
		sportsUtilities.readJsonFile("src/main/resources/json/unique-associations.json");
		return sportsUtilities.getJsonFileReader().getObjectsMap();
	}
	
	public List<WebPage> getPages() {
		return pages;
	}
}
