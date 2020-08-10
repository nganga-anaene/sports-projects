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
public class AssociationCrawler {
    @Autowired
    private SportsUtilities sportsUtilities;
    @Autowired
    private SeleniumBrowsers seleniumBrowsers;
    @Autowired
    private Conditions conditions;
    @Autowired
    private JsoupBrowsers jsoupBrowsers;
    private final String confedsUrls = "confederation-urls";
    private final String memeberId = "Members";

    public AssociationCrawler() {
    }
    
    public void init() {
        sportsUtilities.readJsonFile(confedsUrls);
    }
    
    
    //uses Selenium
    public void setSeleniumAssociationWebPages() {
        seleniumBrowsers.setBrowsers(sportsUtilities.partitionWebPages(
                sportsUtilities.getWebPageLists()
        ));
        seleniumBrowsers.processWebPages(conditions.pageHasId(memeberId));
        seleniumBrowsers.closeBrowsers();
    }
    
    public void setJsoupAssociationWebPages() {
        jsoupBrowsers.setWebDocuments(sportsUtilities.getWebPageLists());
        jsoupBrowsers.processWebPages();
    }
    
    public List<WebPages> getSelenimAssociationWebpages() {
        return seleniumBrowsers.getWebDocuments();
    }
    
    public List<WebPages> getJsoupAssociationWebpages() {
        return jsoupBrowsers.getWebPages();
    }
}
