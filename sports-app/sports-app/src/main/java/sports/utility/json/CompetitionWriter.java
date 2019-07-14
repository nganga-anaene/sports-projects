package sports.utility.json;

import javax.annotation.PostConstruct;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CompetitionWriter {

	private JSONObject teamsObject;
	private JSONObject fixturesObject;

	public CompetitionWriter() {
	}

	@PostConstruct
	private void init() {
		teamsObject = new JSONObject();
		fixturesObject = new JSONObject();
	}

	@SuppressWarnings("unchecked")
	public void addAssociation(String associationName) {
		JSONObject object = (JSONObject) teamsObject.get(associationName);
		if (object == null) {
			teamsObject.put(associationName, new JSONObject());
			fixturesObject.put(associationName, new JSONObject());
		}
	}

	public void addTeamsFixturesUrls(String associationName, String competitionName, String teamsUrl,
			String fixturesUrl) {
		if (teamsObject.get(associationName) != null) {
			JSONObject object = (JSONObject) teamsObject.get(associationName);
			addItem(object, competitionName, teamsUrl);
		}

		if (fixturesObject.get(associationName) != null) {
			JSONObject object = (JSONObject) fixturesObject.get(associationName);
			addItem(object, competitionName, fixturesUrl);
		}
	}

	@SuppressWarnings("unchecked")
	private void addItem(JSONObject object, String key, String value) {
		if (object.get(key) == null) {
			object.put(key, value);
		}
	}

	public JSONObject getFixturesObject() {
		return fixturesObject;
	}

	public JSONObject getTeamsObject() {
		return teamsObject;
	}
}
