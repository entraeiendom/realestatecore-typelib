package io.entraos.rec.mappers;

import com.jayway.jsonpath.PathNotFoundException;
import io.entraos.rec.builders.RoomBuilder;
import io.entraos.rec.domain.Room;
import org.slf4j.Logger;

import static io.entraos.rec.utils.JsonPathHelper.findJsonPathValue;
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
}
