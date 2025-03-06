package cl.pablosilvab.demobackendspringboot.service.strategy;

import cl.pablosilvab.demobackendspringboot.dto.request.ProductCreateDTO;
import cl.pablosilvab.demobackendspringboot.entity.Product;
import cl.pablosilvab.demobackendspringboot.mapper.ProductMapper;
import org.springframework.stereotype.Component;

@Component
public class ElectronicProductCreationStrategy implements ProductCreationStrategy{

    private final ProductMapper productMapper;

    public ElectronicProductCreationStrategy(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Product createProduct(ProductCreateDTO productCreateDTO) {
        Product product = productMapper.toEntity(productCreateDTO);
        product.setPrice(productCreateDTO.price() + 50000);
        return product;
    }

}
