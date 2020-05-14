package io.entraos.rec.mappers;

import io.entraos.rec.domain.Sensor;
import io.entraos.rec.utils.JsonPathHelper;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class SensorJsonMapper {
    private static final Logger log = getLogger(SensorJsonMapper.class);

    public static Sensor fromJson(String json) {
        Sensor sensor = null;
        if (json != null) {
           String type = JsonPathHelper.findJsonPathValue(json, "$.@type");
           if (type != null && type.equals("Sensor")) {
               sensor = new Sensor();
           }
        }
        return sensor;
    }
}
