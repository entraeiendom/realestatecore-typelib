package io.entraos.rec.mappers;

import com.jayway.jsonpath.PathNotFoundException;
import io.entraos.rec.builders.BuildingBuilder;
import io.entraos.rec.domain.Building;
import org.slf4j.Logger;

import static io.entraos.rec.utils.JsonPathHelper.findJsonPathValue;
import static org.slf4j.LoggerFactory.getLogger;

public class BuildingJsonMapper {
    private static final Logger log = getLogger(BuildingJsonMapper.class);

    public static Building fromJson(String json) {
        Building building = null;
        try {
            String type = findJsonPathValue(json, "$.class");
            if (type != null && type.equals("Building")) {
                String uuid = findJsonPathValue(json, "$.id");
                String name = findJsonPathValue(json, "$.popularName");
                String tag = findJsonPathValue(json, "$.littera");
                String isPartOfRealEstate = findJsonPathValue(json, "$.isPartOfRealEstate");
                building = new BuildingBuilder().withUuid(uuid)
                        .withName(name)
                        .withTag(tag)
                        .locatedIn(isPartOfRealEstate)
                        .build();
            }
        } catch (PathNotFoundException e) {
            log.debug("Failed to build Building from {}. Reason {}", json, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("Failed to build Building from {}. Reason {}", json, e.getMessage());
        }
        return building;
    }
}
