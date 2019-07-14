package sports.football.parser;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class JsoupParser {

	private Document doc;

	public JsoupParser() {
	}

	public void setDocument(String html) {
		doc = Jsoup.parse(html);
	}

	public void setDocument(Document doc) {
		this.doc = doc;
	}

	public List<Element> getElementsByCss(String query) {
		return doc.select(query);
	}

	public Element getElementById(String id) {
		return doc.getElementById(id);
	}

	public List<Element> getElementsByTag(String tagName) {
		return doc.getElementsByTag(tagName);
	}

	public List<Element> getElementByTag(String query, Element element) {
		return element.getElementsByTag(query);
	}
}
