package io.entraos.rec.mappers;

import io.entraos.rec.domain.Sensor;
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
        assertNull(SensorJsonMapper.fromJson(null));
        assertNull(SensorJsonMapper.fromJson("{}"));
        Sensor sensor = SensorJsonMapper.fromJson(json);
        assertNotNull(sensor);
        assertEquals("https://en.proptechos.com/api/sensor/09a9fe00-414c-4c66-9dd9-8c2c6c6afe46", sensor.getUuid());
        assertEquals(null, sensor.getName());
        assertEquals("01/+001=434.1001-OS001/BACnet Interface/MSTP Network_1192/=563.RT_RY10103-Romtype_3/Application/CO2 Level", sensor.getTag());
        assertEquals("https://en.proptechos.com/api/buildingcomponent/670b079d-6030-4150-8756-dfef7d785387", sensor.getMountedOnDeviceUuid());
        assertEquals(null, sensor.getFactoryId());
    }
}