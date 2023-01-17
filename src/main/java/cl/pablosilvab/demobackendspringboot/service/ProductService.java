package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.exception.NoStockException;
import cl.pablosilvab.demobackendspringboot.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
	Optional<Product> find(long id);
	Product create(Product product);
	Optional<Product> update(long idValue, Product product);
	List<Product> findAll();
	Product minusStock(Product idValue, long quantity) throws NoStockException;
}
