package sports.utility.json;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JsonWriter {

	public JsonWriter() {
	}
	
	public void writeJsonFile(String path, JSONObject object) {
		try(FileWriter writer = new FileWriter(path)){
			writer.write(object.toJSONString());
			writer.flush();
		} catch (IOException e) {
			System.out.println("Cannot write to file");
		}
	}
}
