package io.entraos.rec.mappers;

import io.entraos.rec.domain.RealEstateCore;
import org.slf4j.Logger;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static io.entraos.rec.utils.JsonPathHelper.findJsonPathValue;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Map from
 */
public class BuildingComponentListMapper {
    private static final Logger log = getLogger(BuildingComponentListMapper.class);

    public static List<RealEstateCore> fromJson(String json) {
        List<RealEstateCore> buildingComponents = new ArrayList<>();

        return buildingComponents;
    }

    public static URI findNext(String json) {
        String nextString = findJsonPathValue(json, "$.view.next");
        URI next = null;
        try {
            next = URI.create(nextString);
        } catch (IllegalArgumentException e) {
            log.debug("Failed to build URI from {} in json {}. Reason: {}", nextString, json, e.getMessage());
        }
        return next;
    }

    public static URI findFirst(String json) {
        String firstString = findJsonPathValue(json, "$.view.first");
        URI first = null;
        try {
            first = URI.create(firstString);
        } catch (IllegalArgumentException e) {
            log.debug("Failed to build URI from {} in json {}. Reason: {}", firstString, json, e.getMessage());
        }
        return first;
    }

    public static URI findLast(String json) {
        String lastString = findJsonPathValue(json, "$.view.last");
        URI last = null;
        try {
            last = URI.create(lastString);
        } catch (IllegalArgumentException e) {
            log.debug("Failed to build URI from {} in json {}. Reason: {}", lastString, json, e.getMessage());
        }
        return last;
    }
}
