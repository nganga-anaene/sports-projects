/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.utilities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lee
 */
public class JsonReaderTest {
    
    @Autowired JsonReader reader;
    
    public JsonReaderTest() {
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
     * Test of openJsonFile method, of class JsonReader.
     */
    @Test
    public void testOpenJsonFile() {
        reader.openJsonFile("src/test/java/resources/confederations.json");
        reader.openJsonFile("src/test/java/resources/confederationUrls.json");
    }

    /**
     * Test of getObjectMap method, of class JsonReader.
     */
    @Test
    public void testGetObjectMap() {
    }

    /**
     * Test of getJsonArray method, of class JsonReader.
     */
    @Test
    public void testGetJsonArray() {
    }
    
}
