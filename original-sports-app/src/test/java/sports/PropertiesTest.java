/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sports.utilities.PropertyReader;

/**
 *
 * @author Lee
 */
public class PropertiesTest {
    
    private static PropertyReader reader;
    
    public PropertiesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        reader = new PropertyReader();
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

    @Test
    public void matchStringPropertyValue() {
        String value = reader.getStringProperty("confederations").get();
        System.out.println(value);
        assertTrue(value.equalsIgnoreCase("src/main/resources/football/confeds.json"));
    }
    
    @Test
    public void matchIntegerPropeteryValue() {
        Integer value = reader.getIntegerProperty("max-browsers").orElse(1);
        System.out.println(value);
        assertTrue(value == 4);
    } 
}
