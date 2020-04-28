package io.entraos.rec.domain;

import io.entraos.rec.builders.BuildingBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BuildingTest {

    @Test
    public void locatedIn() {
        Building buildingA = new BuildingBuilder().withName("Building A").withUuid("uuidABC").locatedIn("uuid123").build();
        assertNotNull(buildingA);
        assertEquals("Building A", buildingA.getName());
        assertEquals("uuid123", buildingA.getLocatedInRealestateUuid());
        assertEquals("uuidABC", buildingA.getUuid());
    }
}