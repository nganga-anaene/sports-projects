package sports.utility.web;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

public class WebPage {

    private String key;
    private List<String> urls;
    private List<String> pages;
    private List<Document> webDocs;

    public WebPage() {
    }

    public WebPage(String key, List<String> urls) {
        this.key = key;
        this.urls = urls;
        pages = new ArrayList<>();
        webDocs = new ArrayList<>();
    }

    public void addWebPage(String doc) {
        if (!doc.isEmpty()) {
            pages.add(doc);
        }
    }

    public void addWebDoc(Document doc) {
        webDocs.add(doc);
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public void addUrl(String url) {
        urls.add(url);
    }

    public String getKey() {
        return key;
    }

    public List<String> getUrls() {
        return urls;
    }

    public List<String> getPages() {
        return pages;
    }

    public List<Document> getWebDocs() {
        return webDocs;
    }

    public void setWebDocs(List<Document> webDocs) {
        this.webDocs = webDocs;
    }

    @Override
    public String toString() {
        return "Web Page: [" + key + ", urls=" + urls 
                + ", docs=" + webDocs 
                +"]";
    }
}
