package io.entraos.rec.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BuildingTest {

    @Test
    public void locatedIn() {
        Building buildingA = new BuildingBuilder().withName("Building A").build();
        assertNotNull(buildingA);
        assertEquals("Building A", buildingA.getName());
    }
}