package cl.pablosilvab.demobackendspringboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ProductCreateDTO(
        @Schema(description = "Product name", example = "Smart TV")
        @NotBlank @Size(max = 200)
        String name,
        @Schema(description = "Product description", example = "55 4K UHD Smart TV with HDR")
        String description,
        @Schema(example = "459000")
        long price,
        @Schema(description = "Available stock", example = "25")
        long stock) {
}
