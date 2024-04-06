import org.example.JsonFileLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JsonFileLoaderTest {

    @TempDir
    Path tempDir;

    @Test
    void testLoadDeliveryOptions() throws Exception {
        Path filePath = tempDir.resolve("deliveryOptions.json");
        Files.writeString(filePath, """
                {
                  "Steak (300g)": ["Express Delivery", "Courier"],
                  "Soda (24x330ml)": ["Courier"]
                }
                """);
        Map<String, List<String>> deliveryOptions = JsonFileLoader.loadDeliveryOptions(filePath.toString());
        assertNotNull(deliveryOptions);
        assertEquals(2, deliveryOptions.size());
        assertTrue(deliveryOptions.containsKey("Steak (300g)"));
        assertEquals(List.of("Express Delivery", "Courier"), deliveryOptions.get("Steak (300g)"));
        assertEquals(List.of("Courier"), deliveryOptions.get("Soda (24x330ml)"));
    }

    @Test
    void testLoadBasketItems() throws Exception {
        Path filePath = tempDir.resolve("basketItems.json");
        Files.writeString(filePath, """
                [
                  "Steak (300g)",
                  "Soda (24x330ml)"
                ]
                """);
        List<String> basketItems = JsonFileLoader.loadBasketItems(filePath.toString());
        assertNotNull(basketItems);
        assertEquals(2, basketItems.size());
        assertTrue(basketItems.contains("Steak (300g)"));
        assertTrue(basketItems.contains("Soda (24x330ml)"));
    }
}
