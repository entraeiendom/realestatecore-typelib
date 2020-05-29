package io.entraos.rec.mappers;

import io.entraos.rec.domain.RealEstateCore;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class BuildingListMapperTest {

    private String json = null;
    public static final String JSON_PATH = "rec-building-list.json";

    @Before
    public void setUp() throws Exception {
        ClassLoader classLoader = new BuildingListMapperTest().getClass().getClassLoader();
        File file = new File(classLoader.getResource(JSON_PATH).getFile());
        assertTrue(file.exists());

        //Read File Content
        json = new String(Files.readAllBytes(file.toPath()));
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

    @Test
    public void fromJson() {
        List<RealEstateCore> buildings = BuildingListMapper.fromJson(json);
        assertNotNull(buildings);
        assertEquals(1, buildings.size());
    }
}