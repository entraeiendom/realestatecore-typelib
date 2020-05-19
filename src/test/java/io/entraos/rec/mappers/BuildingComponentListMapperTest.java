package io.entraos.rec.mappers;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class BuildingComponentListMapperTest {

    private String json = null;
    public static final String JSON_PATH = "rec-buildingcomponent-list.json";

    @Before
    public void setUp() throws Exception {
        ClassLoader classLoader = new DeviceJsonMapperTest().getClass().getClassLoader();
        File file = new File(classLoader.getResource(JSON_PATH).getFile());
        assertTrue(file.exists());

        //Read File Content
        json = new String(Files.readAllBytes(file.toPath()));
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

    @Test
    public void fromJson() {
    }

    @Test
    public void findNext() {
        URI expected = URI.create("https://en.proptechos.com/api/realestatecomponent?page=1&size=5");
        assertEquals(expected, BuildingComponentListMapper.findNext(json));
    }

    @Test
    public void findFirst() {
        URI expected = URI.create("https://en.proptechos.com/api/realestatecomponent?page=0&size=5");
        assertEquals(expected, BuildingComponentListMapper.findFirst(json));
    }

    @Test
    public void findLast() {
    }
}