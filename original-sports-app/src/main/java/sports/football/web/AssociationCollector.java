/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sports.football.entities.Associations;
import sports.football.entities.Confederations;
import sports.football.entityManagers.FootballEntitiesManager;
import sports.utilities.SportsUtilities;
import sports.utilities.UniqueAssociations;
import sports.web.JsoupQueries;
import sports.web.WebPages;
import sports.web.WebParser;

/**
 *
 * @author Lee
 */
@Component
public class AssociationCollector {

    @Autowired
    private AssociationCrawler crawler;
    private final JsoupQueries jsoupQueries = JsoupQueries.getInstance();
    @Autowired
    private FootballEntitiesManager fem;
    @Autowired
    private SportsUtilities sportsUtilities;
    @Autowired
    private UniqueAssociations uniqueAssociations;

    public AssociationCollector() {
    }

    //uses Selenium
    public void setSeleniumAssociationWebPages() {
        associationPagesStream().forEach(page -> setStringAssociations(page));
    }

    public void setJsoupAssociationWebpages() {
        jsoupAssociationPagesStream().forEach(page -> setDocumentAssociations(page));
    }

    private void setStringAssociations(WebPages page) {
        sportsUtilities.webPagesStream(page)
                .forEach(doc -> {
                    WebParser parser = sportsUtilities.getStringParser(doc);
                    saveAssociations(parser);
                });
    }

    private void setDocumentAssociations(WebPages page) {
        sportsUtilities.webDocumentsStream(page)
                .forEach(doc -> {
                    WebParser parser = sportsUtilities.getDocumentParser(doc);
                    saveAssociations(parser);
                });
    }

    //uses selenium
    private Stream<WebPages> associationPagesStream() {
        crawler.init();
        crawler.setSeleniumAssociationWebPages();
        return crawler.getSelenimAssociationWebpages().parallelStream();
    }

    private Stream<WebPages> jsoupAssociationPagesStream() {
        crawler.init();
        crawler.setJsoupAssociationWebPages();
        return crawler.getJsoupAssociationWebpages().stream();
    }

    /*remove numbers at the end of name,
    * the wikipedia articles have numbers at the end of some names
    * and unclosed tags which doesn't help seperate the name from the number
     */
    private String formatAssociationName(String name) {
        return name.replaceAll("\\d", "")
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .trim();
    }

    private void saveAssociations(WebParser parser) {
        Confederations confederation = getConfederationId(parser);
        parser.getSelectElements(jsoupQueries.selectAssociationTables())
                .stream()
                .filter(table -> !table.select(jsoupQueries.checkAssociationTable()).isEmpty())
                .flatMap(table -> {
                    return setAssociations(table, parser, confederation);
                }).forEach(fem::saveAssociation);
    }

    private Stream<Associations> setAssociations(Element table, WebParser parser, Confederations confedId) {
        List<Associations> list = new ArrayList<>();
        parser.getElementsFromSelectedElement(table, jsoupQueries.selectRows())
                .stream().forEach(row -> {
                    if (row.children().size() > 1) {
                        String name = formatAssociationName(row.child(1).text());
                        String associationAbb = row.child(0).text();
                        Associations a = new Associations(name, associationAbb);
                        a.setConfederationId(confedId);
                        list.add(a);
                    }
                });
        return list.stream();
    }

    private Confederations getConfederationId(WebParser parser) {
        String key = parser.getElementById(jsoupQueries.getAssociationsConfederationName()).text();
        if (key.length() < 15) {
            return fem.getConfederationsByAbb(key);
        }
        return fem.getConfederationsByName(key);
    }

    public void saveUniqueAssociations() {
        uniqueAssociations.getAssociations()
                .forEach(association -> {
                    association.setConfederationId(fem.getConfederationsByAbb(association.getAssociationAbb()));
                    fem.saveAssociation(association);
                });
    }

    /**/
}
