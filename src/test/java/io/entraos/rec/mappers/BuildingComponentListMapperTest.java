package io.entraos.rec.mappers;

import io.entraos.rec.domain.RealEstateCore;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.util.List;

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
        List<RealEstateCore> buildingComponents = BuildingComponentListMapper.fromJson(json);
        assertNotNull(buildingComponents);
        assertEquals(5, buildingComponents.size());
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
        URI expected = URI.create("https://en.proptechos.com/api/realestatecomponent?page=160&size=5");
        assertEquals(expected, BuildingComponentListMapper.findLast(json));
    }

    @Test
    public void findCount() {
        Number count = BuildingComponentListMapper.findCount(json);
        assertEquals(count, BuildingComponentListMapper.findCount(json));
    }

    @Test
    public void findFromProptechOs() throws Exception {
        ClassLoader classLoader = new DeviceJsonMapperTest().getClass().getClassLoader();
        File file = new File(classLoader.getResource("proptechos-buildingcomponents-find.json").getFile());
        assertTrue(file.exists());

        //Read File Content
        json = new String(Files.readAllBytes(file.toPath()));
        assertNotNull(json);
        assertFalse(json.isEmpty());
        List<RealEstateCore> buildingComponents = BuildingComponentListMapper.fromJson(json);
        assertNotNull(buildingComponents);
        assertEquals(2, buildingComponents.size());

    }
}