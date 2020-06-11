package io.entraos.rec.mappers;

import io.entraos.rec.domain.RealEstateCore;
import io.entraos.rec.domain.Room;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class RoomListMapperTest {

    private String json = null;
    public static final String JSON_PATH = "rec-room-list.json";

    @Before
    public void setUp() throws Exception {
        ClassLoader classLoader = new RoomListMapperTest().getClass().getClassLoader();
        File file = new File(classLoader.getResource(JSON_PATH).getFile());
        assertTrue(file.exists());

        //Read File Content
        json = new String(Files.readAllBytes(file.toPath()));
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

    @Test
    public void fromJson() {
        List<RealEstateCore> rooms = RoomListMapper.fromJson(json);
        assertNotNull(rooms);
        assertEquals(2, rooms.size());
    }

    @Test
    public void minimalData() {
        List<RealEstateCore> rooms = RoomListMapper.fromJson(json);
        assertNotNull(rooms);
        assertEquals(2, rooms.size());
        Room first = (Room)rooms.get(0);
        assertNotNull(first);
        assertEquals("686a2788-85d5-4e7a-9f3c-823fd2c1aac5", first.getUuid());
        assertEquals("0101", first.findRoomName());
    }
}