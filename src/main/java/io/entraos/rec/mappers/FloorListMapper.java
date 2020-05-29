package io.entraos.rec.mappers;

import com.jayway.jsonpath.PathNotFoundException;
import io.entraos.rec.domain.Floor;
import io.entraos.rec.domain.RealEstateCore;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.jayway.jsonpath.JsonPath.read;
import static io.entraos.rec.utils.JsonPathHelper.getDocument;
import static org.slf4j.LoggerFactory.getLogger;

public class FloorListMapper {
    private static final Logger log = getLogger(FloorListMapper.class);

    public static List<RealEstateCore> fromJson(String json) {
        List<RealEstateCore> floors= new ArrayList<>();

        Object document = getDocument(json);
        Object floorObj =  null;
        try {
            floorObj = read(document, "$.member[*]");
        } catch (PathNotFoundException e) {
            floorObj = read(document, "$.content[*]");
        }
        if (floorObj instanceof JSONArray) {
            for (int i = 0; i < ((JSONArray) floorObj).size(); i++) {
                Object floorJson = ((JSONArray) floorObj).get(i);
                if (floorJson instanceof LinkedHashMap) {
                    Floor floor = FloorJsonMapper.fromLinkedHashMap((LinkedHashMap)floorJson);
                    if (floor != null) {
                        floors.add(floor);
                    }
                }
            }
        }
        return floors;
    }
}
