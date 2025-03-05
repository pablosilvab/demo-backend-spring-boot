package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.dto.model.ProductDTO;
import cl.pablosilvab.demobackendspringboot.dto.request.ProductCreateDTO;
import cl.pablosilvab.demobackendspringboot.dto.response.ProductResponseDTO;
import cl.pablosilvab.demobackendspringboot.entity.Product;
import cl.pablosilvab.demobackendspringboot.exception.ProductNotFoundException;
import cl.pablosilvab.demobackendspringboot.mapper.ProductMapper;
import cl.pablosilvab.demobackendspringboot.repository.ProductRepository;
import cl.pablosilvab.demobackendspringboot.utils.ProductFormatter;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductFormatter productFormatter;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, ProductFormatter productFormatter) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productFormatter = productFormatter;
    }

    @Override
    public ProductResponseDTO getProductById(long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .map(product -> productMapper.toResponseDTO(product, productFormatter))
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    @Transactional
    @Override
    public ProductDTO createProduct(ProductCreateDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(long idValue, ProductDTO productDTO) {
        return null;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> productMapper.toResponseDTO(product, productFormatter))
                .toList();
    }

}
