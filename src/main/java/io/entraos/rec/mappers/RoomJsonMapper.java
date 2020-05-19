package io.entraos.rec.mappers;

import com.jayway.jsonpath.PathNotFoundException;
import io.entraos.rec.builders.RoomBuilder;
import io.entraos.rec.domain.Room;
import org.slf4j.Logger;

import java.util.LinkedHashMap;

import static io.entraos.rec.utils.JsonPathHelper.findJsonPathValue;
import static io.entraos.rec.utils.JsonPathHelper.findString;
import static org.slf4j.LoggerFactory.getLogger;

public class RoomJsonMapper {
    private static final Logger log = getLogger(RoomJsonMapper.class);

    public static Room fromJson(String json) {
        Room room = null;
        try {
            String type = findJsonPathValue(json, "$.class");
            if (type != null && type.equals("Room")) {
                //this.uuid, this.name, this.tag, this.mountedOnDeviceUuid,this.factoryId
                String uuid = findJsonPathValue(json, "$.id");
                String name = findJsonPathValue(json, "$.popularName");
                String tag = findJsonPathValue(json, "$.littera");
                String locatedInBuilding = findJsonPathValue(json, "$.isPartOfBuilding");
                String locatedAtFloor = findJsonPathValue(json, "$.isPartOfStorey");
                room = new RoomBuilder().withUuid(uuid)
                        .withName(name)
                        .withTag(tag)
                        .inBuilding(locatedInBuilding)
                        .atFloor(locatedAtFloor)
                        .build();

            }
        } catch (PathNotFoundException e) {
            log.debug("Failed to build Room from {}. Reason {}", json, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("Failed to build Room from {}. Reason {}", json, e.getMessage());
        }
        return room;
    }

    public static Room fromLinkedHashMap(LinkedHashMap jsonMap) {
        Room room = null;

        String type = null;
        try {
            type = parseType(jsonMap);
            if (type != null && type.equals("Room")) {
                //this.uuid, this.name, this.tag, this.mountedOnDeviceUuid,this.factoryId

                String uuid = parseId(jsonMap);
                String name = findString(jsonMap, "popularName");
                String tag = findString(jsonMap, "littera");
                String locatedInBuilding = parseBuilding(jsonMap);
                String locatedAtFloor = parseFloor(jsonMap);
                room = new RoomBuilder().withUuid(uuid)
                        .withName(name)
                        .withTag(tag)
                        .inBuilding(locatedInBuilding)
                        .atFloor(locatedAtFloor)
                        .build();


            }
        } catch (PathNotFoundException e) {
            log.debug("Failed to build Room from {}. Reason {}", jsonMap, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("Failed to build Room from {}. Reason {}", jsonMap, e.getMessage());
        }


        return room;
    }

    private static String parseFloor(LinkedHashMap jsonMap) {
        String id;
        try {
            id = findString(jsonMap, "$.isPartOfStorey.@id");
            if (id == null) {
                id = findString(jsonMap, "$.isPartOfStorey");
            }
        } catch (PathNotFoundException e) {
            id = findString(jsonMap, "$.isPartOfStorey");
        }
        return id;
    }

    private static String parseBuilding(LinkedHashMap jsonMap) {
        String id;
        try {
            id = findString(jsonMap, "$.isPartOfBuilding.@id");
            if (id == null) {
                id = findString(jsonMap, "$.isPartOfBuilding");
            }
        } catch (PathNotFoundException e) {
            id = findString(jsonMap, "$.isPartOfBuilding");
        }
        return id;
    }

    private static String parseId(LinkedHashMap jsonMap) {
        String id;
        try {
            id = findString(jsonMap, "$.@id");
            if (id == null) {
                id = findString(jsonMap, "$.id");
            }
        } catch (PathNotFoundException e) {
            id = findString(jsonMap, "$.id");
        }
        return id;
    }

    private static String parseType(LinkedHashMap jsonMap) {
        String type;
        try {
            type = findString(jsonMap, "$.@type");
            if (type == null) {
                type = findString(jsonMap, "$.class");
            }
        } catch (PathNotFoundException e) {
            type = findString(jsonMap, "$.class");
        }
        return type;
    }
}
