package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class BasketSplitter {
    private final Map<String, List<String>> DELIVERY_OPTIONS;

    /**
     * Constructs a BasketSplitter with delivery options loaded from a specified JSON file.
     *
     * @param absolutePathToConfigFile The absolute path to the JSON file containing delivery options for products.
     */
    public BasketSplitter(String absolutePathToConfigFile) {
        this.DELIVERY_OPTIONS = JsonFileLoader.loadDeliveryOptions(absolutePathToConfigFile);
    }

    /**
     * Splits the given list of items into delivery groups based on the available delivery options.
     *
     * @param items A list of item names to be split into delivery groups.
     * @return A map where each key is a delivery method and the value is a list of items that should be delivered using that method.
     */
    public Map<String, List<String>> split(List<String> items) {
        if (items.isEmpty()) {
            return new HashMap<>();
        }

        String mostCommonDelivery = findMostCommonDeliveryMethod(items);

        List<String> itemsForMostCommonMethod = new ArrayList<>();
        List<String> remainingItems = new ArrayList<>();

        for (String item : items) {
            if (DELIVERY_OPTIONS.get(item).contains(mostCommonDelivery)) {
                itemsForMostCommonMethod.add(item);
            } else {
                remainingItems.add(item);
            }
        }

        Map<String, List<String>> result = new HashMap<>();
        result.put(mostCommonDelivery, itemsForMostCommonMethod);

        if (!remainingItems.isEmpty()) {
            result.putAll(split(remainingItems));
        }

        return result;
    }

    /**
     * Finds the most common delivery method among the given items.
     *
     * @param items A list of item names for which to find the most common delivery method.
     * @return The name of the most common delivery method among the given items.
     */
    private String findMostCommonDeliveryMethod(List<String> items) {
        Map<String, Integer> deliveryCount = new HashMap<>();
        items.forEach(item -> DELIVERY_OPTIONS.get(item).forEach(deliveryMethod ->
                deliveryCount.put(deliveryMethod, deliveryCount.getOrDefault(deliveryMethod, 0) + 1)));
        return Collections.max(deliveryCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    /**
     * The main method to run the BasketSplitter from the command line.
     *
     * @param args Command line arguments where args[0] is the path to the configuration JSON file and args[1] is the path to the basket JSON file.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar <pathToJarFile> <pathToConfigFile> <pathToBasketFile>");
            System.exit(1);
        }

        String configFilePath = args[0];
        String basketFilePath = args[1];

        BasketSplitter splitter = new BasketSplitter(configFilePath);
        List<String> basketItems = JsonFileLoader.loadBasketItems(basketFilePath);
        Map<String, List<String>> deliveryGroups = splitter.split(basketItems);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(deliveryGroups);
            System.out.println(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
