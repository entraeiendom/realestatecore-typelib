package io.entraos.rec.mappers;

import io.entraos.rec.domain.Device;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class DeviceJsonMapperTest {

    private String json = null;
    public static final String JSON_PATH = "rec-device.json";

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
        assertNull(SensorJsonMapper.fromJson(null));
        assertNull(SensorJsonMapper.fromJson("{}"));
        Device device = DeviceJsonMapper.fromJson(json);
        assertNotNull(device);
        assertEquals("https://en.proptechos.com/api/device/5dc009d6-d3c3-4381-bebd-910b0aca3a78", device.getUuid());
        assertEquals(null, device.getName());
        assertEquals(null, device.getTag());
        assertEquals("https://en.proptechos.com/api/buildingcomponent/670b079d-6030-4150-8756-dfef7d785387", device.getMountedInBuildingComponentUuid());
    }
}