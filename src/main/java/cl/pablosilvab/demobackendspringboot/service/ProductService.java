package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.dto.ProductDTO;
import cl.pablosilvab.demobackendspringboot.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
	ProductDTO getProductById(long id) throws ProductNotFoundException;
	ProductDTO createProduct(ProductDTO productDTO);
	ProductDTO updateProduct(long idValue, ProductDTO productDTO);
	List<ProductDTO> getAllProducts();
}
