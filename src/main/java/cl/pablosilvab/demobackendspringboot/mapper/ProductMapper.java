package cl.pablosilvab.demobackendspringboot.mapper;

import cl.pablosilvab.demobackendspringboot.dto.ProductDTO;
import cl.pablosilvab.demobackendspringboot.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO productDTO);

}
