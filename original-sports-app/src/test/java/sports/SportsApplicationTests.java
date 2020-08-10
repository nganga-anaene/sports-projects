package sports;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sports.utilities.JsonReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SportsApplicationTests {
    
    @Autowired
    JsonReader reader;

    @Test
    public void contextLoads() {
        reader.openJsonFile("src/test/java/resources/confederationUrls.json");
        String item = reader.getObjectMap()
                .get("uefa")
                .toString();
        assert item.equalsIgnoreCase("https://en.wikipedia.org/wiki/UEFA");
        
        item = reader.getObjectMap()
                .get("caf")
                .toString();
        assert item.equalsIgnoreCase("https://en.wikipedia.org/wiki/Confederation_of_African_Football");
    }

}
