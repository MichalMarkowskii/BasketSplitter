import org.example.BasketSplitter;
import org.example.JsonFileLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketSplitterTest {

    private BasketSplitter splitter;

    @BeforeEach
    void setUp() {
        splitter = new BasketSplitter("data/config.json");
    }

    @Test
    void testSplitBasedOnBasket1() {
        List<String> basketItems = JsonFileLoader.loadBasketItems("data/basket-1.json");
        Map<String, List<String>> expected = new HashMap<>();
        expected.put("Pick-up point", Arrays.asList("Fond - Chocolate"));
        expected.put("Courier", Arrays.asList(
                "Cocoa Butter",
                "Tart - Raisin And Pecan",
                "Table Cloth 54x72 White",
                "Flower - Daisies",
                "Cookies - Englishbay Wht"
        ));
        Map<String, List<String>> actual = splitter.split(basketItems);
        assertEquals(expected, actual);
    }

    @Test
    void testSplitBasedOnBasket2() {
        List<String> basketItems = JsonFileLoader.loadBasketItems("data/basket-2.json");
        Map<String, List<String>> expected = new HashMap<>();
        expected.put("Same day delivery", Arrays.asList("Sauce - Mint", "Numi - Assorted Teas", "Garlic - Peeled"));
        expected.put("Courier", Arrays.asList("Cake - Miini Cheesecake Cherry"));
        expected.put("Express Collection", Arrays.asList(
                "Fond - Chocolate", "Chocolate - Unsweetened", "Nut - Almond, Blanched, Whole", "Haggis",
                "Mushroom - Porcini Frozen", "Longan", "Bag Clear 10 Lb", "Nantucket - Pomegranate Pear",
                "Puree - Strawberry", "Apples - Spartan", "Cabbage - Nappa", "Bagel - Whole White Sesame",
                "Tea - Apple Green Tea"
        ));
        Map<String, List<String>> actual = splitter.split(basketItems);
        assertEquals(expected, actual);
    }
}
