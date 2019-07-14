package sports.utility;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.football.parser.JsoupParser;
import sports.utility.json.JsonFileReader;
import sports.utility.json.JsonWriter;

@Component
public class SportsUtilities {

	@Autowired
	private JsonFileReader jsonReader;
	@Autowired
	private JsonWriter jsonWriter;
	private Map<String, Object> preferredNames;
	private Calendar currentMatchDay;

	public SportsUtilities() {
	}

	public void readJsonFile(String path) {
		jsonReader.openJsonFile(path);
	}

	public JsonFileReader getJsonFileReader() {
		return jsonReader;
	}

	public Document getWebDocument(String url) {
		try {
			return Jsoup.connect(url).get();
		} catch (IOException e) {
			return null;
		}
	}

	public String worldFootballUrl() {
		return "https://www.worldfootball.net";
	}

	@PostConstruct
	private void readPreferredNames() {
		jsonReader.openJsonFile("src/main/resources/json/preferred-names.json");
		preferredNames = jsonReader.getObjectsMap();
	}

	public void writeJsonFile(String path, JSONObject object) {
		jsonWriter.writeJsonFile(path, object);
	}

	public String getPreferredName(String key) {
		String name = (String) preferredNames.get(key);
		if (name != null) {
			return name.trim();
		} else
			return key.trim();
	}

	public String getTeamsUrlPath() {
		return "src/main/resources/json/teams-url.json";
	}

	public String getFixturesUrlPath() {
		return "src/main/resources/json/fixtures-url.json";
	}

	public String getTeamSelectOptions() {
		return "select[name='saison'] > option";
	}

	public String getSelectedAttribute() {
		return "selected";
	}

	public String getSeasonId(JsoupParser parser) {
		return parser.getElementsByCss(getTeamSelectOptions()).stream()
				.filter(element -> element.attr(getSelectedAttribute()).equalsIgnoreCase(getSelectedAttribute()))
				.findFirst().get().text();
	}

	public Calendar getCurrentMatchDay() {
		return currentMatchDay;
	}

	public void setCurrentMatchDay(int[] date) {
		if (date != null) {
			Calendar temp = Calendar.getInstance();
			temp.clear();
			temp.set(date[0], date[1], date[2]);
			this.currentMatchDay = temp;
		}
	}
}
