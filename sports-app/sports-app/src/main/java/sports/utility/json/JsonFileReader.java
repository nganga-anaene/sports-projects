package sports.utility.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class JsonFileReader {

	private JSONObject object;
	private Map<String, Object> map;

	public JsonFileReader() {
	}

	public void openJsonFile(String path) {
		try (FileReader reader = new FileReader(path)) {
			JSONParser parser = new JSONParser();
			object = (JSONObject) parser.parse(reader);
			setJsonObjects();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + path);
		} catch (IOException e) {
			System.out.println("Problem with I/O: " + path);
		} catch (ParseException e) {
			System.out.println("Unable to parse file: " + path);
		}
	}

	@SuppressWarnings("unchecked")
	private void setJsonObjects() {
		map = new HashMap<>();
		object.keySet().forEach(key -> {
			map.put(key.toString(), object.get(key));
		});
	}

	public Map<String, Object> getObjectsMap() {
		return map;
	}

	public JSONObject getMapJsonObject(String key) {
		return (JSONObject) map.get(key);
	}

	public JSONArray getMapJsonArray(String key) {
		return (JSONArray) map.get(key);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getInnerJsonObject(Object object) {
		JSONObject obj = (JSONObject) object;
		Map<String, String> temp = new HashMap<>();
		obj.keySet().forEach(key -> {
			temp.put(key.toString(), (String) obj.get(key));
		});
		return temp;
	}
}
