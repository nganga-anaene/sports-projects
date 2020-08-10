/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.utilities;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sports.web.WebPages;
import sports.web.WebParser;

/**
 *
 * @author Lee
 */
@Component
public class SportsUtilities {

    @Autowired
    private JsonReader jsonReader;
    @Autowired
    private PropertyReader propertyReader;
    @Autowired
    private CollectionUtilities collectionUtilities;
    @Autowired
    private JsonWriter jsonWriter;

    public SportsUtilities() {
    }

    public void readJsonFile(String propertyKey) {
        String path = propertyReader.getStringProperty(propertyKey).orElse("");
        jsonReader.openJsonFile(path);
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }
    
    public int getIntegerProperty(String property) {
        return propertyReader.getIntegerProperty(property).get();
    }

    public PropertyReader getPropertyReader() {
        return propertyReader;
    }

    public CollectionUtilities getCollectionUtilities() {
        return collectionUtilities;
    }

    private String getMapKeyString(Map.Entry key) {
        return key.getKey().toString();
    }

    private String getMapValue(Map.Entry key) {
        return (String) key.getValue();
    }

    private List<String> getMapValueArray(Map.Entry key) {
        return (List<String>) key.getValue();
    }

    private WebPages getMapValueStringWebPage(Map.Entry key) {
        return new WebPages(getMapKeyString(key), Arrays.asList(getMapValue(key)));
    }

    private WebPages getMapValueArrayWebPage(Map.Entry key) {
        return new WebPages(getMapKeyString(key), getMapValueArray(key));
    }

    public List<WebPages> getWebPageLists() {
        return jsonReader.getObjectMapStream()
                .map(key -> getMapValueStringWebPage(key))
                .collect(Collectors.toList());
    }

    public List<List<WebPages>> partitionWebPages(List<WebPages> pages) {
        return collectionUtilities.getPartitionedWebPages(pages);
    }

    public List<WebPages> getWebPagesLists() {
        return jsonReader.getObjectMapStream()
                .map(key -> getMapValueArrayWebPage(key))
                .collect(Collectors.toList());
    }

    public Map<String, Object> getJsonObjectMap() {
        return jsonReader.getObjectMap();
    }

    public Stream<String> webPagesStream(WebPages page) {
        return page.getPages().parallelStream();
    }

    public Stream<Document> webDocumentsStream(WebPages page) {
        return page.getWebDocs().parallelStream();
    }

    public WebParser getStringParser(String doc) {
        WebParser parser = new WebParser();
        parser.parseString(doc);
        return parser;
    }

    public WebParser getDocumentParser(Document doc) {
        WebParser parser = new WebParser();
        parser.setDocument(doc);
        return parser;
    }

    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    public String getCompetitionUrls() {
        return "competition-urls-json";
    }

    public List<WebPages> setInnerJsonUrls(String key) {
        return jsonReader.getJsonObjects(jsonReader.getJsonObject(key))
                .entrySet()
                .stream()
                .map(entry -> getMapValueArrayWebPage(entry))
                .collect(Collectors.toList());
    }

    //check if start of string has an integer 
    //or an alpha numeric starting with an integer
    //remove the integer
    public String parseName(String name) {
        String[] temp = name.split(" ");
        if (Character.isDigit(temp[0].charAt(0))) {
            try {
                Integer.parseInt(temp[0].trim());
                return name.replaceFirst(temp[0], "").trim();
            } catch (NumberFormatException e) {
                return name.substring(2).trim();
            }
        }
        return name.trim();
    }
    
    //check if string has alpha numeric or interger at begining of string
    //move alpha numeric value to end of the string
    public String swapIntegerPostion(String name) {
        String[] temp = name.split(" ");
        if (Character.isDigit(temp[0].charAt(0))) {
            try {
                Integer.parseInt(temp[0].trim());
                String string = name.replaceFirst(temp[0], "");
                return (string + " " + temp[0]).trim();
            } catch (NumberFormatException e) {
                String string = name.substring(2);
                return (string + " " + temp[0].replaceAll("\\.", "")).trim();
            }
        }
        return name.trim();
    }
    
    //remove unnecessary string from season
    public String parseSeason(String season) {
        return season.split(" ")[0].trim();
    }
}
