package io.entraos.rec.mappers;

import io.entraos.rec.domain.Building;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class BuildingJsonMapperTest {

    private String json = null;
    public static final String JSON_PATH = "rec-building.json";

    @Before
    public void setUp() throws Exception {
        ClassLoader classLoader = new BuildingJsonMapperTest().getClass().getClassLoader();
        File file = new File(classLoader.getResource(JSON_PATH).getFile());
        assertTrue(file.exists());

        //Read File Content
        json = new String(Files.readAllBytes(file.toPath()));
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

    @Test
    public void fromJson() {
        assertNull(BuildingJsonMapper.fromJson(null));
        assertNull(BuildingJsonMapper.fromJson("{}"));
        Building building = BuildingJsonMapper.fromJson(json);
        assertNotNull(building);
        assertEquals("e93bc342-33e2-4c70-ae8e-b4801839ae11", building.getUuid());
        assertEquals("Bygning 001", building.getName());
        assertEquals(null, building.getTag());
        assertEquals("1dbb6518-704b-4b77-93af-70369b1d765c", building.getLocatedInRealestateUuid());
    }
}