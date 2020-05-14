package io.entraos.rec.mappers;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class SensorJsonMapperTest {

    private String json = null;
    public static final String JSON_PATH = "rec-sensor.json";

    @Before
    public void setUp() throws Exception {
        ClassLoader classLoader = new SensorJsonMapperTest().getClass().getClassLoader();
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
}