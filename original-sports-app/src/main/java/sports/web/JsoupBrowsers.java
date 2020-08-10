/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.web;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lee
 */
@Component
public class JsoupBrowsers {

    private List<WebPages> pages;

    public JsoupBrowsers() {
    }

    public void setWebDocuments(List<WebPages> pages) {
        this.pages = pages;
    }

    public void processWebPages() {
        pages.parallelStream().forEach(page -> {
            page.getUrls().stream().forEach(url -> {
                addWebDocument(page, url);
            });
        });
    }
    
    public WebPages processWebPage(WebPages page) {
        page.getUrls().stream().forEach(url -> {
            addWebDocument(page, url);
        });
        return page;
    }

    public Document getWebDocument(String url) throws IOException {
        return Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
    }

    public void addWebDocument(WebPages page, String url) {
        try {
            Document doc = getWebDocument(url);
            page.addWebDoc(doc);
        } catch (IOException ex) {
            Logger.getLogger(JsoupBrowsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<WebPages> getWebPages() {
        return pages;
    }

}
