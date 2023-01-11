package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Product;
import cl.pablosilvab.demobackendspringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product find(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product create(Product product) {
        Product saved = productRepository.save(product);
        return saved;
    }

    @Override
    public Product update(long id, Product product) {
        Product projectById = this.find(id);
        if (projectById == null) return null;
        else {
            projectById.setName(product.getName());
            projectById.setDescription(product.getDescription());
            projectById.setPrice(product.getPrice());
            return productRepository.save(projectById);
        }
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
