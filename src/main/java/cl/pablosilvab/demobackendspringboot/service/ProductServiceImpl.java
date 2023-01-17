package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.exception.NoStockException;
import cl.pablosilvab.demobackendspringboot.model.Product;
import cl.pablosilvab.demobackendspringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Optional<Product> find(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product create(Product product) {
        Product saved = productRepository.save(product);
        return saved;
    }

    @Override
    public Optional<Product> update(long id, Product product) {
        Optional<Product> p = this.find(id);
        if (p.isPresent()) {
            Product productById = p.get();
            productById.setName(product.getName());
            productById.setDescription(product.getDescription());
            productById.setPrice(product.getPrice());
            productById.setStock(product.getStock());
            return Optional.of(productRepository.save(productById));
        } else {
          return Optional.empty();
        }
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product minusStock(Product product, long quantity) throws NoStockException {
        long currentStock = product.getStock();
        if (currentStock >= quantity) {
            product.setStock(currentStock - quantity);
            return productRepository.save(product);
        } else {
            throw new NoStockException("No stock available");
        }
    }

}
