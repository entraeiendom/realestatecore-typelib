package io.entraos.rec.mappers;

import com.jayway.jsonpath.PathNotFoundException;
import io.entraos.rec.domain.Building;
import io.entraos.rec.domain.RealEstateCore;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.jayway.jsonpath.JsonPath.read;
import static io.entraos.rec.utils.JsonPathHelper.getDocument;
import static org.slf4j.LoggerFactory.getLogger;

public class BuildingListMapper {
    private static final Logger log = getLogger(BuildingListMapper.class);

    public static List<RealEstateCore> fromJson(String json) {
        List<RealEstateCore> buildings = new ArrayList<>();

        Object document = getDocument(json);
        Object realEtates =  null;
        try {
            realEtates = read(document, "$.member[*]");
        } catch (PathNotFoundException e) {
            realEtates = read(document, "$.content[*]");
        }
        if (realEtates instanceof JSONArray) {
            for (int i = 0; i < ((JSONArray) realEtates).size(); i++) {
                Object realEstateJson = ((JSONArray) realEtates).get(i);
                if (realEstateJson instanceof LinkedHashMap) {
                    Building building = BuildingJsonMapper.fromLinkedHashMap((LinkedHashMap)realEstateJson);
                    if (building != null) {
                        buildings.add(building);
                    }
                }
            }
        }
        return buildings;
    }


}
