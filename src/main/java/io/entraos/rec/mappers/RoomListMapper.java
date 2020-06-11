package io.entraos.rec.mappers;

import com.jayway.jsonpath.PathNotFoundException;
import io.entraos.rec.domain.RealEstateCore;
import io.entraos.rec.domain.Room;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.jayway.jsonpath.JsonPath.read;
import static io.entraos.rec.utils.JsonPathHelper.getDocument;
import static org.slf4j.LoggerFactory.getLogger;

public class RoomListMapper {
    private static final Logger log = getLogger(RoomListMapper.class);

    public static List<RealEstateCore> fromJson(String json) {
        List<RealEstateCore> rooms = new ArrayList<>();

        Object document = getDocument(json);
        Object roomsArray =  null;
        if (document instanceof JSONArray) {
            roomsArray = document;
        } else {
            try {
                roomsArray = read(document, "$.member[*]");
            } catch (PathNotFoundException e) {
                roomsArray = read(document, "$.content[*]");
            }
        }
        if (roomsArray instanceof JSONArray) {
            for (int i = 0; i < ((JSONArray) roomsArray).size(); i++) {
                Object roomJson = ((JSONArray) roomsArray).get(i);
                if (roomJson instanceof LinkedHashMap) {
                    Room room = RoomJsonMapper.fromLinkedHashMap((LinkedHashMap)roomJson);
                    if (room != null) {
                        rooms.add(room);
                    }
                }
            }
        }
        return rooms;
    }
}
