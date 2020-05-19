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
                String locatedAtFloor = findJsonPathValue(json,"$.isPartOfStorey");
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
            try {
                type = findString(jsonMap, "@type");
            }catch (PathNotFoundException e) {
                type = findString(jsonMap, "class");
            }
            if (type != null && type.equals("Room")) {
                //this.uuid, this.name, this.tag, this.mountedOnDeviceUuid,this.factoryId

                String uuid = findString(jsonMap, "id");
                String name = findString(jsonMap, "popularName");
                String tag = findString(jsonMap, "littera");
                String locatedInBuilding = findString(jsonMap, "isPartOfBuilding");
                String locatedAtFloor = findString(jsonMap,"isPartOfStorey");
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
}
