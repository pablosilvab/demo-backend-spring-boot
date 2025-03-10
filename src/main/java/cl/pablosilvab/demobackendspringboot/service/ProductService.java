package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.dto.model.ProductDTO;
import cl.pablosilvab.demobackendspringboot.dto.request.ProductCreateDTO;
import cl.pablosilvab.demobackendspringboot.dto.response.ProductResponseDTO;
import cl.pablosilvab.demobackendspringboot.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponseDTO getProductById(long id) throws ProductNotFoundException;

    ProductDTO createProduct(ProductCreateDTO productDTO);

    ProductDTO updateProduct(long idValue, ProductDTO productDTO);

    Page<ProductResponseDTO> getAllProducts(Pageable pageable);
}
