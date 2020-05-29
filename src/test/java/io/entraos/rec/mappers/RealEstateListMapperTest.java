package io.entraos.rec.mappers;

import io.entraos.rec.domain.RealEstateCore;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class RealEstateListMapperTest {

    private String json = null;
    public static final String JSON_PATH = "rec-realestate-list.json";

    @Before
    public void setUp() throws Exception {
        ClassLoader classLoader = new RealEstateListMapper().getClass().getClassLoader();
        File file = new File(classLoader.getResource(JSON_PATH).getFile());
        assertTrue(file.exists());

        //Read File Content
        json = new String(Files.readAllBytes(file.toPath()));
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

    @Test
    public void fromJson() {
        List<RealEstateCore> realEstates = RealEstateListMapper.fromJson(json);
        assertNotNull(realEstates);
        assertEquals(1, realEstates.size());
    }
}