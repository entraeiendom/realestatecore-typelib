package io.entraos.rec.mappers;

import com.jayway.jsonpath.PathNotFoundException;
import io.entraos.rec.builders.FloorBuilder;
import io.entraos.rec.domain.Floor;
import org.slf4j.Logger;

import java.util.LinkedHashMap;

import static io.entraos.rec.utils.JsonPathHelper.findJsonPathValue;
import static io.entraos.rec.utils.JsonPathHelper.findString;
import static org.slf4j.LoggerFactory.getLogger;

public class FloorJsonMapper {
    private static final Logger log = getLogger(FloorJsonMapper.class);

    public static Floor fromJson(String json) {
        Floor floor = null;
       try {
           String type = findJsonPathValue(json, "$.class");
           if (type != null && type.equals("Storey")) {
               String uuid = findJsonPathValue(json, "$.id");
               String name = findJsonPathValue(json, "$.popularName");
               String tag = findJsonPathValue(json, "$.littera");
               String isPartOfBuilding = findJsonPathValue(json, "$.isPartOfBuilding");
               floor = new FloorBuilder().withUuid(uuid)
                       .withName(name)
                       .withTag(tag)
                       .inBuilding(isPartOfBuilding)
                       .build();
           }
        } catch (PathNotFoundException e) {
            log.debug("Failed to build Floor from {}. Reason {}", json, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("Failed to build Floor from {}. Reason {}", json, e.getMessage());
        }
        return floor;
    }

    public static Floor fromLinkedHashMap(LinkedHashMap jsonMap) {
        Floor floor = null;

        String type = null;
        try {
            type = findString(jsonMap, "$.class");
            if (type != null && type.equals("Storey")) {
                //this.uuid, this.name, this.tag, this.mountedOnDeviceUuid,this.factoryId

                String uuid = findString(jsonMap, "id");
                String name = findString(jsonMap, "popularName");
                String tag = findString(jsonMap, "littera");
                String isPartOfBuilding = findString(jsonMap, "isMountedInBuildingComponent.@id");
                floor = new FloorBuilder().withUuid(uuid)
                        .withName(name)
                        .withTag(tag)
                        .inBuilding(isPartOfBuilding)
                        .build();


            }
        } catch (PathNotFoundException e) {
            log.debug("Failed to build RealEstate from {}. Reason {}", jsonMap, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("Failed to build RealEstate from {}. Reason {}", jsonMap, e.getMessage());
        }


        return floor;
    }
}
