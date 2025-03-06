package cl.pablosilvab.demobackendspringboot.utils;

import org.springframework.stereotype.Component;

@Component
public class ProductFormatter {

    public String formatPrice(long price) {
        return "$" + String.format("%,d", price);
    }

    public String getAvailabilityStatus(long stock) {
        return stock > 0 ? "Disponible" : "Agotado";
    }
}
