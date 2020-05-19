package io.entraos.rec.utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;

import static com.jayway.jsonpath.JsonPath.read;

/**
 * Attribution Cantara https://github.com/Cantara/Whydah-TypeLib/blob/master/src/main/java/net/whydah/sso/basehelpers/JsonPathHelper.java
 */
public class JsonPathHelper {

    private static final Logger log = LoggerFactory.getLogger(JsonPathHelper.class);

    public static Object getDocument(String jsonString) {
        return Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
    }

    public static List<String> findJsonpathList(String jsonString, String expression) throws PathNotFoundException {
        List<String> result = null;
        try {
            Object document = getDocument(jsonString);
            result = read(document, expression);

        } catch (Exception e) {
            log.warn("Failed to parse JSON. Expression {}, JSON {}, ", expression, jsonString, e);
        }
        return result;
    }

    public static String findJsonPathValue(String jsonString, String expression) throws PathNotFoundException {
        return JsonPath.parse(jsonString).read(expression);
    }

    public static Number findJsonPathNumber(String jsonString, String expression) throws PathNotFoundException {
        return JsonPath.parse(jsonString).read(expression);
    }

    public static String findJsonPathNullableValue(String jsonString, String expression) {
        try {
            return findJsonPathValue(jsonString, expression);
        } catch (PathNotFoundException ex) {
            return null;
        }
    }

    public static String getStringFromJsonpathExpression(String jsonString, String expression) throws PathNotFoundException {
        //String expression = "$.identity.uid";
        String value = "";
        Object document = getDocument(jsonString);
        String result = read(document, expression);
        value = result.toString();

        return value;
    }


    public static JSONArray getJsonArrayFromJsonpathExpression(String jsonString, String expression) throws PathNotFoundException {
        Object document = getDocument(jsonString);
        return read(document, expression);
    }

    /**
     * @param jsonString The JSON document you are parsing
     * @param expression An JSONPath expression
     * @return The resulting JSON Array converted to a String Array
     * @throws PathNotFoundException
     */
    public static String[] getStringArrayFromJsonpathExpression(String jsonString, String expression) throws PathNotFoundException {
        Object document = getDocument(jsonString);
        String resArray = read(document, expression);
        String resString = resArray.toString().substring(1, resArray.toString().lastIndexOf("]") - 1);
        resString = resString.replace("},{", " ,");
        String[] array = resString.split(" ");
        return array;

    }

    /**
     * @param jsonString The JSON document you are parsing
     * @param expression An JSONPath expression
     * @return The resulting JSON Array converted to a String Array
     * @throws PathNotFoundException
     */
    public static String getStringFromJsonpathArrayExpression(String jsonString, String expression) throws PathNotFoundException {
        Object document = getDocument(jsonString);
        String resArray = read(document, expression);
        return resArray;

    }

    /**
     * @param jsonString
     * @param expression
     * @return
     * @throws PathNotFoundException
     */
    public static LinkedHashMap getJsonObjectFromJsonpathExpression(String jsonString, String expression) throws PathNotFoundException {
        Object document = getDocument(jsonString);
        return read(document, expression);
    }

    /**
     *
     * @param jsonMap
     * @param key
     * @return
     * @throws PathNotFoundException
     */
    public static String findString(LinkedHashMap<String, Object> jsonMap, String key) throws PathNotFoundException {
        String value = null;

        /*
        if (!jsonMap.containsKey(key)) {
            throw new PathNotFoundException(key + " was not found in " + jsonMap);
        }
        */
        try {
            value = read(jsonMap, key);
        } catch (Exception e) {
            log.debug("Failed to find {} in {}", key, jsonMap);
        }
        return value;


    }


}
