package io.entraos.rec.mappers;

import com.jayway.jsonpath.PathNotFoundException;
import io.entraos.rec.domain.RealEstateCore;
import io.entraos.rec.domain.Room;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.jayway.jsonpath.JsonPath.read;
import static io.entraos.rec.utils.JsonPathHelper.*;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Map from
 */
public class BuildingComponentListMapper {
    private static final Logger log = getLogger(BuildingComponentListMapper.class);

    public static List<RealEstateCore> fromJson(String json) {
        List<RealEstateCore> buildingComponents = new ArrayList<>();

        Object document = getDocument(json);
        Object rooms =  null;
        try {
            rooms = read(document, "$.member[*]");
        } catch (PathNotFoundException e) {
            rooms = read(document, "$.content[*]");
        }
        if (rooms instanceof JSONArray) {
            for (int i = 0; i < ((JSONArray) rooms).size(); i++) {
                Object roomJson = ((JSONArray) rooms).get(i);
                if (roomJson instanceof LinkedHashMap) {
                    Room room = RoomJsonMapper.fromLinkedHashMap((LinkedHashMap)roomJson);
                    if (room != null) {
                        buildingComponents.add(room);
                    }
                }
            }
        }
        return buildingComponents;
    }

    public static URI findNext(String json) {
        String nextString = findJsonPathValue(json, "$.view.next");
        URI next = null;
        try {
            next = URI.create(nextString);
        } catch (PathNotFoundException | IllegalArgumentException e) {
            log.debug("Failed to build URI from {} in json {}. Reason: {}", nextString, json, e.getMessage());
        }
        return next;
    }

    public static URI findFirst(String json) {
        String firstString = findJsonPathValue(json, "$.view.first");
        URI first = null;
        try {
            first = URI.create(firstString);
        } catch (PathNotFoundException | IllegalArgumentException e) {
            log.debug("Failed to build URI from {} in json {}. Reason: {}", firstString, json, e.getMessage());
        }
        return first;
    }

    public static URI findLast(String json) {
        String lastString = findJsonPathValue(json, "$.view.last");
        URI last = null;
        try {
            last = URI.create(lastString);
        } catch (PathNotFoundException | IllegalArgumentException e) {
            log.debug("Failed to build URI from {} in json {}. Reason: {}", lastString, json, e.getMessage());
        }
        return last;
    }

    public static Number findCount(String json) {
        Number count = null;
        try {
           count = findJsonPathNumber(json, "$.totalItems");
        } catch (PathNotFoundException | IllegalArgumentException e) {
            try {
                count = findJsonPathNumber(json, "$.totalElements");
            } catch (PathNotFoundException | IllegalArgumentException ne) {
                log.debug("Failed to find \"totalItems\" in json {}. Reason: {}", json, e.getMessage());
            }
        }
        return count;
    }
}
