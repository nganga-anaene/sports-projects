/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lee limit selenium browsers to 4 initialise the browsers return
 * browsers to calling Class allow them to be closed later
 */
@Component
public class SeleniumBrowsers {

    private List<SeleniumBrowser> browserList;

    public SeleniumBrowsers() {
    }

    public void setBrowsers(List<List<WebPages>> webPages) {
        browserList = new ArrayList<>();
        for (int i = 0; i < webPages.size(); i++) {
            browserList.add(addBrowser(webPages.get(i)));
        }
        browserList.forEach(b -> b.initBrowser());
    }

    private SeleniumBrowser addBrowser(List<WebPages> pages) {
        SeleniumBrowser b = new SeleniumBrowser();
        b.setPages(pages);
        return b;
    }

    public void processWebPages(ExpectedCondition condition) {
        browserList.parallelStream()
                .forEach(browser -> browser.processWebPage(condition));
    }

    public List<WebPages> getWebDocuments() {
        return browserList.stream().flatMap(browser -> {
            return browser.getPages().stream();
        }).collect(Collectors.toList());
    }

    public void closeBrowsers() {
        browserList.forEach(b -> b.closeBrowser());
    }

    public List<SeleniumBrowser> getBrowsers() {
        return browserList;
    }
}
