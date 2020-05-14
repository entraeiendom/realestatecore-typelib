package io.entraos.rec.mappers;

import io.entraos.rec.builders.SensorBuilder;
import io.entraos.rec.domain.Sensor;
import org.slf4j.Logger;

import static io.entraos.rec.utils.JsonPathHelper.findJsonPathValue;
import static org.slf4j.LoggerFactory.getLogger;

public class SensorJsonMapper {
    private static final Logger log = getLogger(SensorJsonMapper.class);

    public static Sensor fromJson(String json) {
        Sensor sensor = null;
        if (json != null) {
           String type = findJsonPathValue(json, "$.@type");
           if (type != null && type.equals("Sensor")) {
               //this.uuid, this.name, this.tag, this.mountedOnDeviceUuid,this.factoryId
               String uuid = findJsonPathValue(json, "$.@id");
               String name = findJsonPathValue(json, "$.popularName");
               String tag = findJsonPathValue(json, "$.littera");
               String mountedOnDeviceUuid = findJsonPathValue(json, "$.isMountedInBuildingComponent.@id");
               String factoryId = null; //TODO findJsonPathValue(json, "TODO");
               sensor = new SensorBuilder().withUuid(uuid)
                       .withName(name)
                       .withTag(tag)
                       .onDevice(mountedOnDeviceUuid)
                       .withFactoryId(factoryId)
                       .build();
           }
        }
        return sensor;
    }
}
