/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.web;

import java.util.List;
import java.util.stream.Stream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lee
 */
@Component
public class WebParser {

    private Document doc;

    public WebParser() {
    }

    public void parseString(String webPage) {
        doc = Jsoup.parse(webPage);
    }

    public void setDocument(Document doc) {
        this.doc = doc;
    }

    public List<Element> getSelectElements(String selector) {
        return doc.select(selector);
    }

    public Element getElementById(String id) {
        return doc.getElementById(id);
    }
    
    public String getTitle() {
        return doc.title();
    }

    public List<Element> getElementsFromSelectedElement(Element element, String selector) {
        return element.select(selector);
    }

    public List<Element> getElementsByTag(String tag) {
        return doc.getElementsByTag(tag);
    }

    public String getName(Element element, String query) {
        return getElementsFromSelectedElement(element, query).get(0).text();
    }

    public String getAttribteValue(Element element, String query, String attr) {
        return getElementsFromSelectedElement(element, query).get(0).attr(attr);
    }
    
    public Stream<Element> elementStream(String query) {
        return getSelectElements(query).stream();
    }
}
