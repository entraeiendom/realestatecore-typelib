package io.entraos.rec.mappers;

import com.jayway.jsonpath.PathNotFoundException;
import io.entraos.rec.builders.DeviceBuilder;
import io.entraos.rec.domain.Device;
import org.slf4j.Logger;

import java.util.LinkedHashMap;

import static io.entraos.rec.utils.JsonPathHelper.findJsonPathValue;
import static io.entraos.rec.utils.JsonPathHelper.findString;
import static org.slf4j.LoggerFactory.getLogger;

public class DeviceJsonMapper {
    private static final Logger log = getLogger(DeviceJsonMapper.class);

    public static Device fromJson(String json) {
        Device device = null;
       try {
           String type = findJsonPathValue(json, "$.@type");
           if (type != null && type.equals("Device")) {
               String uuid = findJsonPathValue(json, "$.@id");
               String name = findJsonPathValue(json, "$.popularName");
               String tag = findJsonPathValue(json, "$.littera");
               String mountedInBuildingComponentUuid = findJsonPathValue(json, "$.isMountedInBuildingComponent.@id");
               String factoryId = null; //aka mac address. TODO findJsonPathValue(json, "TODO");
               device = new DeviceBuilder().withUuid(uuid)
                       .withName(name)
                       .withTag(tag)
                       .mountedInBuildingComponent(mountedInBuildingComponentUuid)
                       .withFactoryId(factoryId)
                       .build();
           }
        } catch (PathNotFoundException e) {
            log.debug("Failed to build Device from {}. Reason {}", json, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("Failed to build Device from {}. Reason {}", json, e.getMessage());
        }
        return device;
    }

    public static Device fromLinkedHashMap(LinkedHashMap jsonMap) {
        Device device = null;

        String type = null;
        try {
            type = findString(jsonMap, "$.class");
            if (type != null && type.equals("Device")) {
                //this.uuid, this.name, this.tag, this.mountedOnDeviceUuid,this.factoryId

                String uuid = findString(jsonMap, "id");
                String name = findString(jsonMap, "popularName");
                String tag = findString(jsonMap, "littera");
                String isPartOfBuilding = findString(jsonMap, "isMountedInBuildingComponent.@id");
                device = new DeviceBuilder().withUuid(uuid)
                        .withName(name)
                        .withTag(tag)
                        .mountedInBuildingComponent(isPartOfBuilding)
                        .build();


            }
        } catch (PathNotFoundException e) {
            log.debug("Failed to build Device from {}. Reason {}", jsonMap, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("Failed to build Device from {}. Reason {}", jsonMap, e.getMessage());
        }


        return device;
    }
}
