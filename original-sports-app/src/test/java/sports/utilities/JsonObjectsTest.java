/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
public class JsonObjectsTest {

    public JsonObjectsTest() {
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
     * Test of getNewObject method, of class JsonObjects.
     */
    @Test
    public void testGetNewObject() {
        JsonObjects objects = new JsonObjects();
        JSONObject object = new JSONObject();
        object.put("Welldone", "We on the way");
        JSONArray array = new JSONArray();
        array.add("yes");
        array.add("why");
        array.add("no");
        objects.addJsonObject("Some object", object);
        object.put("Hello", array);
        objects.addJsonArray("Hey Now Hey Now", array);
        System.out.println(objects.getObject().toJSONString());
        objects.getJsonArrayObject("Hey Now Hey Now").add("Yes we can go far now");
        System.out.println(objects.getObject().toJSONString());
    }

}
