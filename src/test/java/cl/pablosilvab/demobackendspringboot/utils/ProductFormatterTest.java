package cl.pablosilvab.demobackendspringboot.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductFormatterTest {

    private ProductFormatter productFormatter;

    @BeforeEach
    void setUp() {
        productFormatter = new ProductFormatter();
    }

    @Test
    void testFormatPrice_ShouldFormatPriceCorrectly() {
        long price = 459000;
        String formattedPrice = productFormatter.formatPrice(price);
        assertEquals("$459.000", formattedPrice);

        // Test con precio con valores más grandes
        price = 1000000000;
        formattedPrice = productFormatter.formatPrice(price);
        assertEquals("$1.000.000.000", formattedPrice);
    }

    @Test
    void testGetAvailabilityStatus_ShouldReturnAvailable_WhenStockIsPositive() {
        long stock = 10;
        String availabilityStatus = productFormatter.getAvailabilityStatus(stock);
        assertEquals("Disponible", availabilityStatus);
    }

    @Test
    void testGetAvailabilityStatus_ShouldReturnOutOfStock_WhenStockIsZero() {
        long stock = 0;
        String availabilityStatus = productFormatter.getAvailabilityStatus(stock);
        assertEquals("Agotado", availabilityStatus);
    }

    @Test
    void testGetAvailabilityStatus_ShouldReturnOutOfStock_WhenStockIsNegative() {
        long stock = -5;
        String availabilityStatus = productFormatter.getAvailabilityStatus(stock);
        assertEquals("Agotado", availabilityStatus);
    }
}
