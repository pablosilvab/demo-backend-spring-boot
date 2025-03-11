package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.dto.model.ProductDTO;
import cl.pablosilvab.demobackendspringboot.dto.request.ProductCreateDTO;
import cl.pablosilvab.demobackendspringboot.dto.response.ProductResponseDTO;
import cl.pablosilvab.demobackendspringboot.entity.Product;
import cl.pablosilvab.demobackendspringboot.entity.ProductType;
import cl.pablosilvab.demobackendspringboot.exception.InvalidProductTypeException;
import cl.pablosilvab.demobackendspringboot.exception.ProductNotFoundException;
import cl.pablosilvab.demobackendspringboot.mapper.ProductMapper;
import cl.pablosilvab.demobackendspringboot.repository.ProductRepository;
import cl.pablosilvab.demobackendspringboot.service.strategy.ProductCreationStrategy;
import cl.pablosilvab.demobackendspringboot.service.strategy.ProductCreationStrategyFactory;
import cl.pablosilvab.demobackendspringboot.utils.ProductFormatter;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductFormatter productFormatter;
    private final ProductCreationStrategyFactory productCreationStrategyFactory;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper,
                              ProductFormatter productFormatter,
                              ProductCreationStrategyFactory productCreationStrategyFactory) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productFormatter = productFormatter;
        this.productCreationStrategyFactory = productCreationStrategyFactory;
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
        String type = productDTO.type().toUpperCase();
        if (!EnumUtils.isValidEnum(ProductType.class, type)) {
            throw new InvalidProductTypeException("Invalid product type: " + type +
                    ". Valid types are: " + Arrays.stream(ProductType.values())
                    .map(Enum::name)
                    .collect(Collectors.joining(", ")));
        }

        ProductType productType = ProductType.valueOf(productDTO.type().toUpperCase());
        ProductCreationStrategy strategy = productCreationStrategyFactory.getStrategy(productType);
        Product product = strategy.createProduct(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(long idValue, ProductDTO productDTO) {
        return null;
    }

    @Override
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return productRepository
                .findAll(pageable)
                .map(product -> productMapper.toResponseDTO(product, productFormatter));
    }

}
