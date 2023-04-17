package cl.pablosilvab.demobackendspringboot.repository;

import cl.pablosilvab.demobackendspringboot.model.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    Product product;

    @BeforeAll
    public void setUp() {
        product = new Product(0, "Soundbar Xiaomi", "Soundbar", 55000, 50);
        productRepository.save(product);
    }

    @Test
    @Order(1)
    public void testSave() {
        Product p = new Product(0, "Soundbar Xiaomi", "Soundbar", 55000, 50);
        Product response = productRepository.save(p);
        Assertions.assertNotNull(response);
    }

    @Test
    @Order(2)
    public void testFindById(){
        Optional<Product> response = productRepository.findById(5L);
        Assertions.assertFalse(response.isPresent());
    }

    /**
     * Method to remove all travels test data.
     *
     * @author Mariana Azevedo
     * @since 24/03/2020
     */
    @AfterAll
    public void tearDown() {
        productRepository.deleteAll();
    }

}
