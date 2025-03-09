package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.dto.model.ProductDTO;
import cl.pablosilvab.demobackendspringboot.dto.request.ProductCreateDTO;
import cl.pablosilvab.demobackendspringboot.entity.Product;
import cl.pablosilvab.demobackendspringboot.entity.ProductType;
import cl.pablosilvab.demobackendspringboot.mapper.ProductMapper;
import cl.pablosilvab.demobackendspringboot.repository.ProductRepository;
import cl.pablosilvab.demobackendspringboot.service.strategy.ProductCreationStrategy;
import cl.pablosilvab.demobackendspringboot.service.strategy.ProductCreationStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    public static final String PRODUCT_NAME = "Smart TV 55' 4K UHD";
    public static final String PRODUCT_DESCRIPTION = "55 4K UHD Smart TV with HDR and Dolby Vision";
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductCreationStrategyFactory productCreationStrategyFactory;

    @Mock
    private ProductCreationStrategy productCreationStrategy;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductCreateDTO productCreateDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productCreateDTO = ProductCreateDTO.builder().name(PRODUCT_NAME).description(PRODUCT_DESCRIPTION).price(459000L).stock(25L).type("ELECTRONIC").build();
    }


    @Test
    void testCreateProduct() {
        // Arrange
        Product product = new Product(null, PRODUCT_NAME, PRODUCT_DESCRIPTION, 459000L, 25L, ProductType.ELECTRONIC);
        Product savedProduct = new Product(1L, PRODUCT_NAME, PRODUCT_DESCRIPTION, 459000L, 25L, ProductType.ELECTRONIC);
        ProductDTO productDTO = new ProductDTO(1L, PRODUCT_NAME, PRODUCT_DESCRIPTION, 459000L, 25L, ProductType.ELECTRONIC.toString());

        // Mocking
        when(productCreationStrategyFactory.getStrategy(ProductType.ELECTRONIC)).thenReturn(productCreationStrategy);
        when(productCreationStrategy.createProduct(productCreateDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(savedProduct);
        when(productMapper.toDTO(savedProduct)).thenReturn(productDTO);

        // Act
        ProductDTO result = productService.createProduct(productCreateDTO);

        // Assert
        assertNotNull(result);
        assertEquals(PRODUCT_NAME, result.name());
        assertEquals(ProductType.ELECTRONIC.toString(), result.type());

        // Verify interactions
        verify(productCreationStrategyFactory).getStrategy(ProductType.ELECTRONIC);
        verify(productCreationStrategy).createProduct(productCreateDTO);
        verify(productRepository).save(product);
        verify(productMapper).toDTO(savedProduct);
    }

}
