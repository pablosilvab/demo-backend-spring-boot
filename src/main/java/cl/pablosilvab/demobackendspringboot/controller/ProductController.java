package cl.pablosilvab.demobackendspringboot.controller;

import cl.pablosilvab.demobackendspringboot.model.Product;
import cl.pablosilvab.demobackendspringboot.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String id) {
        try {
            long idValue = Long.parseLong(id);
            Product foundProject = productService.find(idValue);
            if (foundProject == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(foundProject);
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Product> create(@RequestBody Product project) {
        log.info("new project {}", project.toString());
        Product newProject = productService.create(project);
        if (newProject == null) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(newProject, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable String id) {
        try {
            long idValue = Long.parseLong(id);
            Product updatedProject = productService.update(idValue, product);
            if (updatedProject == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(updatedProject);
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
