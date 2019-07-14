package sports.football.entity.manager;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.football.entity.Confederation;
import sports.utility.SportsUtilities;

@Component
public class ConfederationsManager {

	@Autowired
	private SportsUtilities sportsUtilities;
	@Autowired
	private FootballEntityManager fem;
	private final String path = "src/main/resources/json/confederations.json";
	
	public ConfederationsManager() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public void readConfederations() {
		sportsUtilities.readJsonFile(path);
		sportsUtilities
		.getJsonFileReader()
		.getMapJsonArray("confederations")
		.stream()
		.forEach(this::saveConfederation);
	}
	
	public void saveConfederation(Object item) {
		JSONObject object = (JSONObject) item;
		int id = Integer.parseInt(object.get("id").toString());
		String name = (String) object.get("name");
		String regionName = (String) object.get("regionName");
		String abb = (String) object.get("abb");
		Confederation confederation = new Confederation(id, name, regionName, abb);
		fem.saveConfederation(confederation);
	}
}
