package io.entraos.rec.mappers;

import io.entraos.rec.domain.RealEstate;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class RealEstateJsonMapperTest {

    private String json = null;
    public static final String JSON_PATH = "rec-realestate.json";

    @Before
    public void setUp() throws Exception {
        ClassLoader classLoader = new RealEstateJsonMapperTest().getClass().getClassLoader();
        File file = new File(classLoader.getResource(JSON_PATH).getFile());
        assertTrue(file.exists());

        //Read File Content
        json = new String(Files.readAllBytes(file.toPath()));
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

    @Test
    public void fromJson() {
        assertNull(SensorJsonMapper.fromJson(null));
        assertNull(SensorJsonMapper.fromJson("{}"));
        RealEstate realEstate = RealEstateJsonMapper.fromJson(json);
        assertNotNull(realEstate);
        assertEquals("1dbb6518-704b-4b77-93af-70369b1d765c", realEstate.getUuid());
        assertEquals("Jurfak", realEstate.getName());
        assertEquals(null, realEstate.getTag());
    }
}