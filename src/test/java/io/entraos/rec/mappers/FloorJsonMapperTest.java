package io.entraos.rec.mappers;

import io.entraos.rec.domain.Floor;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class FloorJsonMapperTest {

    private String json = null;
    public static final String JSON_PATH = "rec-floor.json";

    @Before
    public void setUp() throws Exception {
        ClassLoader classLoader = new FloorJsonMapperTest().getClass().getClassLoader();
        File file = new File(classLoader.getResource(JSON_PATH).getFile());
        assertTrue(file.exists());

        //Read File Content
        json = new String(Files.readAllBytes(file.toPath()));
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

    @Test
    public void fromJson() {
        assertNull(FloorJsonMapper.fromJson(null));
        assertNull(FloorJsonMapper.fromJson("{}"));
        Floor floor = FloorJsonMapper.fromJson(json);
        assertNotNull(floor);
        assertEquals("d2e74e37-67e2-45f1-9c6e-5080dfbd7abc", floor.getUuid());
        assertEquals("0", floor.getName());
        assertEquals("0", floor.getTag());
        assertEquals("e93bc342-33e2-4c70-ae8e-b4801839ae11", floor.getLocatedInUuid());
    }

}