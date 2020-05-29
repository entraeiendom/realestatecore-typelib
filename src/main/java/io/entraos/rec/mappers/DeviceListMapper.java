package io.entraos.rec.mappers;

import com.jayway.jsonpath.PathNotFoundException;
import io.entraos.rec.domain.Device;
import io.entraos.rec.domain.RealEstateCore;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.jayway.jsonpath.JsonPath.read;
import static io.entraos.rec.utils.JsonPathHelper.getDocument;
import static org.slf4j.LoggerFactory.getLogger;

public class DeviceListMapper {
    private static final Logger log = getLogger(DeviceListMapper.class);

    public static List<RealEstateCore> fromJson(String json) {
        List<RealEstateCore> devices = new ArrayList<>();

        Object document = getDocument(json);
        Object devicesArray =  null;
        if (document instanceof JSONArray) {
            devicesArray = document;
        } else {
            try {
                devicesArray = read(document, "$.member[*]");
            } catch (PathNotFoundException e) {
                devicesArray = read(document, "$.content[*]");
            }
        }
        if (devicesArray instanceof JSONArray) {
            for (int i = 0; i < ((JSONArray) devicesArray).size(); i++) {
                Object deviceJson = ((JSONArray) devicesArray).get(i);
                if (deviceJson instanceof LinkedHashMap) {
                    Device device = DeviceJsonMapper.fromLinkedHashMap((LinkedHashMap)deviceJson);
                    if (device != null) {
                        devices .add(device);
                    }
                }
            }
        }
        return devices ;
    }
}
