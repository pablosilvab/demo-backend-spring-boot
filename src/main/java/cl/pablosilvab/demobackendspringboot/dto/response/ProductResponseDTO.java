package cl.pablosilvab.demobackendspringboot.dto.response;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        String formattedPrice,
        String availabilityStatus) {
}
