package io.entraos.rec.mappers;

import com.jayway.jsonpath.PathNotFoundException;
import io.entraos.rec.domain.RealEstate;
import io.entraos.rec.domain.RealEstateCore;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.jayway.jsonpath.JsonPath.read;
import static io.entraos.rec.utils.JsonPathHelper.getDocument;
import static org.slf4j.LoggerFactory.getLogger;

public class RealEstateListMapper {
    private static final Logger log = getLogger(RealEstateListMapper.class);

    public static List<RealEstateCore> fromJson(String json) {
        List<RealEstateCore> realEstates = new ArrayList<>();

        Object document = getDocument(json);
        Object singleEstate =  null;
        try {
            singleEstate = read(document, "$.member[*]");
        } catch (PathNotFoundException e) {
            singleEstate = read(document, "$.content[*]");
        }
        if (singleEstate instanceof JSONArray) {
            for (int i = 0; i < ((JSONArray) singleEstate).size(); i++) {
                Object realEstateJson = ((JSONArray) singleEstate).get(i);
                if (realEstateJson instanceof LinkedHashMap) {
                    RealEstate realEstate = RealEstateJsonMapper.fromLinkedHashMap((LinkedHashMap)realEstateJson);
                    if (realEstate != null) {
                        realEstates.add(realEstate);
                    }
                }
            }
        }
        return realEstates;
    }
}
