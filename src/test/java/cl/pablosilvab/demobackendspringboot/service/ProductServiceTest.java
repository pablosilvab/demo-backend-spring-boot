package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Product;
import cl.pablosilvab.demobackendspringboot.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    @Order(1)
    public void testSave() {
        BDDMockito.given(productRepository.save(Mockito.any(Product.class)))
                .willReturn(getMockProduct());
        Product response = productService.create(new Product());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.getId());
    }

    @Test
    @Order(2)
    public void testFindById() {
        BDDMockito.given(productRepository.findById(Mockito.anyLong()))
                .willReturn(Optional.of(getMockProduct()));
        Optional<Product> response = productService.find(1);
        Assertions.assertTrue(response.isPresent());
    }

    private Product getMockProduct() {
        Product product = new Product(1L,
                "Soundbar Samsung",
                "Soundbar Samsung",
                150000,
                100);
        return product;
    }

    @AfterAll
    public void tearDown() {
        productRepository.deleteAll();
    }

}
