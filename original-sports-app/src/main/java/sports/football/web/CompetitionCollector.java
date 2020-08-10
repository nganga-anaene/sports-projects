/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sports.football.entityManagers.FootballEntitiesManager;
import sports.utilities.CompetitionDetails;
import sports.utilities.SportsUtilities;
import sports.web.JsoupQueries;
import sports.web.WebPages;
import sports.web.WebParser;

/**
 *
 * @author Lee
 */
@Component
public class CompetitionCollector {

    @Autowired
    private CompetitionCrawler crawler;
    @Autowired
    private SportsUtilities sportsUtilities;
    private final JsoupQueries jsoupQueries = JsoupQueries.getInstance();
    private List<CompetitionDetails> competitionDetailsList;
    @Autowired
    private FootballEntitiesManager fem;

    public CompetitionCollector() {
    }

    public void init() {
        competitionDetailsList = new ArrayList<>();
    }

    public void setSeleniumCompetitionDetails() {
        competitionPagesStream().forEach(page -> setCompetitionDetails(page));
    }

    public void setJsoupCompetitionDetails() {
        jsoupCompetitionPagesStream().forEach(page -> setJsoupCompetitionDetails(page));
    }

    private void setCompetitionDetails(WebPages page) {
        sportsUtilities.webPagesStream(page).forEach(doc -> {
            WebParser parser = sportsUtilities.getStringParser(doc);
            addCompetitionDetails(page.getKey(), parser);
        });
    }

    private void setJsoupCompetitionDetails(WebPages page) {
        sportsUtilities.webDocumentsStream(page).forEach(doc -> {
            WebParser parser = sportsUtilities.getDocumentParser(doc);
            addCompetitionDetails(page.getKey(), parser);
        });
    }

    private Stream<WebPages> jsoupCompetitionPagesStream() {
        crawler.init();
        crawler.setJsoupCompetitions();
        return crawler.getJsoupWebPages().parallelStream();
    }

    private Stream<WebPages> competitionPagesStream() {
        crawler.init();
        crawler.setSeleniumCompetitions();
        return crawler.getSeleniumCompetitionWebPages().parallelStream();
    }

    private void addCompetitionDetails(String key, WebParser parser) {
        CompetitionDetails competitionDetails = new CompetitionDetails();
        competitionDetails.setAssociationName(key);
        competitionDetails.setFixturesUrl(getUrl(jsoupQueries.fixturesString(), parser));
        competitionDetails.setTeamsUrl(getUrl(jsoupQueries.teamsString(), parser));
        competitionDetails.setCompetitionsUrl(getUrl(jsoupQueries.leaguesString(), parser));
        competitionDetails.setCompetitonName(getCompetitionName(parser));
        competitionDetailsList.add(competitionDetails);
    }

    private String getCompetitionName(WebParser parser) {
        return parser.getElementsByTag(jsoupQueries.metaTags())
                .stream()
                .filter(tag -> tag.hasAttr(jsoupQueries.nameAttribute())
                && tag.attr(jsoupQueries.nameAttribute())
                        .equalsIgnoreCase(jsoupQueries.pageTopic())
                ).findAny()
                .get()
                .attr(jsoupQueries.contentAttribute());
    }

    private String getUrl(String compare, WebParser parser) {
        try {
            return jsoupQueries.worldFootballUrl()
                    + parser.getSelectElements(jsoupQueries.selectCompetitionDetails())
                            .stream()
                            .filter(element -> element.text().equalsIgnoreCase(compare))
                            .findAny()
                            .get()
                            .attr(jsoupQueries.hrefAttribute())
                            .trim();
        } catch (Exception e) {
            return "";
        }
    }

    public List<CompetitionDetails> getCompetitionDetailsList() {
        return competitionDetailsList;
    }

    public Stream<CompetitionDetails> getCompetitonDetailsStream() {
        return competitionDetailsList.parallelStream();
    }
}
