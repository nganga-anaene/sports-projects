/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lee
 */
public class WebParserTest {

    private static List<String> urls = Arrays.asList("https://www.worldfootball.net/competition/and-1a-divisio/",
            "https://www.worldfootball.net/competition/aut-bundesliga/",
            "https://www.worldfootball.net/competition/bel-eerste-klasse-a/",
            "https://www.worldfootball.net/competition/cyp-first-division/",
            "https://www.worldfootball.net/competition/kos-superliga/"
    );

    public WebParserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setDocument method, of class WebParser.
     */
    @Test
    public void testDocument() {
//        System.out.println("Competition Names\n---------------");
//        getCompetitionName(urls);
//        System.out.println("\n---------------\nSchedule Urls\n------------------");
//        printUrls(urls);
//        System.out.println("\n-----------------\nSelect Items\n------------------");
//        getSelectOpions();
        System.out.println("\n-----------------\nAssociation Names\n------------------");
        getAssociations(urls);
    }

    private void getCompetitionName(List<String> urls) {
        urls.forEach(url -> {
            try {
                Document doc = Jsoup.connect(url).get();
                List<Element> elements = doc.getElementsByTag("meta");
                elements.stream().filter(tag -> tag.hasAttr("name") && tag.attr("name").equalsIgnoreCase("page-topic")).forEach(tag -> {
                    System.out.println(tag.attr("content"));
                });
            } catch (IOException ex) {
                Logger.getLogger(WebParserTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void getSelectOpions() {
        try {
            Document doc = Jsoup.connect("https://www.worldfootball.net/all_matches/eng-premier-league-2018-2019/").get();
            List<Element> elements = doc.select("#site > div.white > div.content > div > div.box2 > div > table > tbody > tr > td > form > select[name='saison'] > option");
            elements.stream().limit(10).forEach(System.out::println);
        } catch (IOException ex) {
            Logger.getLogger(WebParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void printUrls(List<String> urls) {
        urls.forEach(url -> {
            try {
                Document doc = Jsoup.connect(url).get();
                List<Element> elements = doc.select("#navi > div.sitenavi > div.navibox2 > div > ul > li > a");
                elements.stream().filter(element -> element.text().equalsIgnoreCase("teams")).forEach(element -> {
                    System.out.println(element.attr("href"));
                });
            } catch (IOException ex) {
                Logger.getLogger(WebParserTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void getAssociations(List<String> urls) {
        urls.forEach(url -> {
            try {
                Document doc = Jsoup.connect(url).get();
                List<Element> elements = doc.select("#topnavi > ul > li.active > a");
                elements.stream().forEach(element -> System.out.println(element.text().trim()));
            } catch (IOException ex) {
                Logger.getLogger(WebParserTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
