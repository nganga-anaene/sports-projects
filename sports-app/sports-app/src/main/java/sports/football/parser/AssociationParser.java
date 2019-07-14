package sports.football.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.football.entity.Association;
import sports.football.entity.manager.FootballEntityManager;
import sports.utility.web.WebPage;

@Component
public class AssociationParser {

	private final String tableElements = "#mw-content-text > div > table.wikitable.sortable.hlist.jquery-tablesorter > tbody > tr";
	@Autowired
	private FootballEntityManager fem;
	@Autowired
	private JsoupParser jsoupParser;
	private List<Association> associations;

	public AssociationParser() {
	}

	public void parseConfederationPages(List<WebPage> pages) {
		associations = new ArrayList<>();
		pages.parallelStream().forEach(page -> {
			page.getPages().stream().forEach(p -> {
				jsoupParser.setDocument(p);
				jsoupParser.getElementsByCss(tableElements).forEach(element -> {
					List<Element> elements = jsoupParser.getElementByTag("td", element);
					setAssociations(elements, page.getKey());
				});
			});
		});
	}

	private void setAssociations(List<Element> elements, String confederationAbb) {
		if (!elements.isEmpty()) {
			if (elements.get(0).text().length() < 5) {
				String abb = elements.get(0).text().trim();
				String name = filterAssociationName(elements.get(1).text());
				associations.add(new Association(abb, name, fem.getConfederationByAbbreviation(confederationAbb.toUpperCase())));
			} else {
				String abb = elements.get(1).text().trim();
				String name = filterAssociationName(elements.get(0).text());
				associations.add(new Association(abb, name, fem.getConfederationByAbbreviation(confederationAbb.toUpperCase())));
			}
		}
	}
	
	private String filterAssociationName(String name) {
		String [] temp = name.split("\\[");
		name = temp[0];
		temp = name.split(" ");
		try {
			Integer.parseInt(temp[temp.length-1].trim());
			return temp[0].trim();
		} catch (Exception e) {
			return name.trim();
		}
	}
	
	public List<Association> getAssociations() {
		return associations;
	}
}
