package io.entraos.rec.mappers;

import com.jayway.jsonpath.PathNotFoundException;
import io.entraos.rec.builders.RealEstateBuilder;
import io.entraos.rec.domain.RealEstate;
import org.slf4j.Logger;

import java.util.LinkedHashMap;

import static io.entraos.rec.utils.JsonPathHelper.findJsonPathValue;
import static io.entraos.rec.utils.JsonPathHelper.findString;
import static org.slf4j.LoggerFactory.getLogger;

public class RealEstateJsonMapper {
    private static final Logger log = getLogger(RealEstateJsonMapper.class);

    public static RealEstate fromJson(String json) {
        RealEstate realEstate = null;
        try {
            String type = findJsonPathValue(json, "$.class");
            if (type != null && type.equals("RealEstate")) {
                String uuid = findJsonPathValue(json, "$.id");
                String name = findJsonPathValue(json, "$.popularName");
                String tag = findJsonPathValue(json, "$.littera");
                realEstate = new RealEstateBuilder().withUuid(uuid)
                        .withName(name)
                        .withTag(tag)
                        .withRawJson(json)
                        .build();
            }
        } catch (PathNotFoundException e) {
            log.debug("Failed to build RealEstate from {}. Reason {}", json, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("Failed to build RealEstate from {}. Reason {}", json, e.getMessage());
        }
        return realEstate;
    }

    public static RealEstate fromLinkedHashMap(LinkedHashMap jsonMap) {
        RealEstate realEstate = null;

        String type = null;
        try {
            type = findString(jsonMap, "$.class");
            if (type != null && type.equals("RealEstate")) {
                //this.uuid, this.name, this.tag, this.mountedOnDeviceUuid,this.factoryId

                String uuid = findString(jsonMap, "id");
                String name = findString(jsonMap, "popularName");
                String tag = findString(jsonMap, "littera");
                realEstate = new RealEstateBuilder().withUuid(uuid)
                        .withName(name)
                        .withTag(tag)
                        .build();


            }
        } catch (PathNotFoundException e) {
            log.debug("Failed to build RealEstate from {}. Reason {}", jsonMap, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("Failed to build RealEstate from {}. Reason {}", jsonMap, e.getMessage());
        }


        return realEstate;
    }
}
