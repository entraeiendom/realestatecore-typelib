package io.entraos.rec.mappers;

import io.entraos.rec.domain.Room;
import io.entraos.rec.utils.JsonPathHelper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class RoomJsonMapperTest {

    private String json = null;
    public static final String JSON_PATH = "rec-buildingcomponent-room.json";

    @Before
    public void setUp() throws Exception {
        ClassLoader classLoader = new RoomJsonMapperTest().getClass().getClassLoader();
        File file = new File(classLoader.getResource(JSON_PATH).getFile());
        assertTrue(file.exists());

        //Read File Content
        json = new String(Files.readAllBytes(file.toPath()));
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

    @Test
    public void fromJson() {
        assertNull(RoomJsonMapper.fromJson(null));
        assertNull(RoomJsonMapper.fromJson("{}"));
        Room room = RoomJsonMapper.fromJson(json);
        assertNotNull(room);
        assertEquals("670b079d-6030-4150-8756-dfef7d785387", room.getUuid());
        assertEquals(null, room.getName());
        assertEquals("10103", room.getTag());
        assertEquals("e93bc342-33e2-4c70-ae8e-b4801839ae11", room.getLocatedInUuid());
        assertEquals("3274ae50-afa2-4177-bc2a-824359ad8aa0", room.getFloorUuid());
    }

    @Test
    public void fromLinkedHashMap() {
        LinkedHashMap jsonMap = JsonPathHelper.getJsonObjectFromJsonpathExpression(json, "$");
        assertNotNull(jsonMap);
        Room room = RoomJsonMapper.fromLinkedHashMap(jsonMap);
        assertNotNull(room);
        assertEquals("670b079d-6030-4150-8756-dfef7d785387", room.getUuid());
        assertEquals(null, room.getName());
        assertEquals("10103", room.getTag());
        assertEquals("e93bc342-33e2-4c70-ae8e-b4801839ae11", room.getLocatedInUuid());
        assertEquals("3274ae50-afa2-4177-bc2a-824359ad8aa0", room.getFloorUuid());
    }
}