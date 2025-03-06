package cl.pablosilvab.demobackendspringboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record ProductCreateDTO(
        @Schema(description = "Product name", example = "Smart TV")
        @NotBlank @Size(max = 200)
        String name,

        @Schema(description = "Product description", example = "55 4K UHD Smart TV with HDR")
        String description,

        @Schema(example = "459000")
        @Digits(integer = 10, fraction = 0, message = "Price must be a valid numeric value")
        @NotNull(message = "Price is required")
        @Positive(message = "Price must be a positive value")
        long price,

        @Schema(description = "Available stock", example = "25")
        @Min(value = 0, message = "Stock cannot be negative")
        long stock,

        @Schema(description = "Product type", example = "ELECTRONIC")
        String type
) {
}
