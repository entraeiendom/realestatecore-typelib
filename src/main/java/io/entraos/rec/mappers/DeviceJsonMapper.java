package io.entraos.rec.mappers;

import com.jayway.jsonpath.PathNotFoundException;
import io.entraos.rec.builders.DeviceBuilder;
import io.entraos.rec.domain.Device;
import org.slf4j.Logger;

import static io.entraos.rec.utils.JsonPathHelper.findJsonPathValue;
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
}
