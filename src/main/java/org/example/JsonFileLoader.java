package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JsonFileLoader {

    /**
     * Loads a JSON file and returns a map containing items and available delivery methods.
     *
     * @param filePath The path to the JSON file.
     * @return A map with product names as keys and a list of delivery methods as values.
     */
    public static Map<String, List<String>> loadDeliveryOptions(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(filePath);
            return mapper.readValue(file, new TypeReference<Map<String, List<String>>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    /**
     * Loads a list of items in a basket from a JSON file.
     *
     * @param filePath The path to the JSON file containing the basket.
     * @return A list of item names in the basket.
     */
    public static List<String> loadBasketItems(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), new TypeReference<List<String>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
