/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.football.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sports.utilities.SportsUtilities;
import sports.web.SeleniumBrowsers;
import sports.web.Conditions;
import sports.web.JsoupBrowsers;
import sports.web.WebPages;

/**
 *
 * @author Lee
 */
@Component
public class CompetitionCrawler {
    @Autowired
    private SportsUtilities sportsUtilities;
    @Autowired
    private SeleniumBrowsers seleniumBrowsers;
    @Autowired
    private Conditions conditions;
    @Autowired
    private JsoupBrowsers jsoupBrowsers;
    private final String majorCountries = "major-countries";
    private final String minorCountries = "minor-countries";
    private final String pageNavigation = "navi";
    private List<WebPages> pages;

    public CompetitionCrawler() {
    }
    
    public void init() {
        sportsUtilities.readJsonFile(majorCountries);
        pages = sportsUtilities.getWebPagesLists();
    }
    
    public void setSeleniumCompetitions() {
        seleniumBrowsers.setBrowsers(sportsUtilities.partitionWebPages(
                pages
        ));
        seleniumBrowsers.processWebPages(conditions.pageHasId(pageNavigation));
        seleniumBrowsers.closeBrowsers();
    }
    
    public void setJsoupCompetitions() {
        jsoupBrowsers.setWebDocuments(sportsUtilities.getWebPagesLists());
        jsoupBrowsers.processWebPages();
    }
    
    public List<WebPages> getSeleniumCompetitionWebPages() {
        return seleniumBrowsers.getWebDocuments();
    }
    
    public List<WebPages> getJsoupWebPages() {
        return jsoupBrowsers.getWebPages();
    }
}
