package cl.pablosilvab.demobackendspringboot.service.strategy;

import cl.pablosilvab.demobackendspringboot.dto.request.ProductCreateDTO;
import cl.pablosilvab.demobackendspringboot.entity.Product;

public interface ProductCreationStrategy {
    Product createProduct(ProductCreateDTO productCreateDTO);
}
