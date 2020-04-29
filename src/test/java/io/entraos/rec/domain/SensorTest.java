package io.entraos.rec.domain;

import io.entraos.rec.builders.SensorBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SensorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void basic() {
        Sensor tempSensor = new SensorBuilder().withName("TempSensor A")
                .withUuid("uuidABC")
                .withTag("TFM123/T")
                .onDevice("uuid123")
                .withFactoryId("mac1234").build();
        assertNotNull(tempSensor);
        assertEquals("TempSensor A", tempSensor.getName());
        assertEquals("uuidABC", tempSensor.getUuid());
        assertEquals("TFM123/T", tempSensor.getTag());
        assertEquals("uuid123", tempSensor.getMountedOnDeviceUuid());
        assertEquals("mac1234", tempSensor.getFactoryId());
    }

}