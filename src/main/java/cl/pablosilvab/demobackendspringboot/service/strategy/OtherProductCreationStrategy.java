package cl.pablosilvab.demobackendspringboot.service.strategy;

import cl.pablosilvab.demobackendspringboot.dto.request.ProductCreateDTO;
import cl.pablosilvab.demobackendspringboot.entity.Product;
import cl.pablosilvab.demobackendspringboot.mapper.ProductMapper;
import org.springframework.stereotype.Component;

@Component
public class OtherProductCreationStrategy implements ProductCreationStrategy {

    private final ProductMapper productMapper;

    public OtherProductCreationStrategy(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Product createProduct(ProductCreateDTO productCreateDTO) {
        return productMapper.toEntity(productCreateDTO);
    }

}
