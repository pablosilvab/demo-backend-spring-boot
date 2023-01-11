package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Product;

import java.util.List;

public interface ProductService {
	Product find(long id);
	Product create(Product product);
	Product update(long idValue, Product product);
	List<Product> findAll();
}
