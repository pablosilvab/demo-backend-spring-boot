package cl.pablosilvab.demobackendspringboot.mapper;

import cl.pablosilvab.demobackendspringboot.dto.model.ProductDTO;
import cl.pablosilvab.demobackendspringboot.dto.request.ProductCreateDTO;
import cl.pablosilvab.demobackendspringboot.dto.response.ProductResponseDTO;
import cl.pablosilvab.demobackendspringboot.entity.Product;
import cl.pablosilvab.demobackendspringboot.utils.ProductFormatter;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = ProductFormatter.class)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);
    Product toEntity(ProductCreateDTO productCreateDTO);
    @Mapping(target = "formattedPrice", expression = "java(productFormatter.formatPrice(product.getPrice()))")
    @Mapping(target = "availabilityStatus", expression = "java(productFormatter.getAvailabilityStatus(product.getStock()))")
    ProductResponseDTO toResponseDTO(Product product, @Context ProductFormatter productFormatter);


}
