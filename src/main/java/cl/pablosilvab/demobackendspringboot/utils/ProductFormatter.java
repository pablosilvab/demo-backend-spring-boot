package cl.pablosilvab.demobackendspringboot.utils;

import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.util.Locale;

@Component
public class ProductFormatter {

    public String formatPrice(long price) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.of("es", "CL"));
        return "$" + numberFormat.format(price);
    }

    public String getAvailabilityStatus(long stock) {
        return stock > 0 ? "Disponible" : "Agotado";
    }
}
