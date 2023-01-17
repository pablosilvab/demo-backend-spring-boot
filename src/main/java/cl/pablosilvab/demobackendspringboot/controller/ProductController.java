package cl.pablosilvab.demobackendspringboot.controller;

import cl.pablosilvab.demobackendspringboot.dto.Response;
import cl.pablosilvab.demobackendspringboot.exception.NoStockException;
import cl.pablosilvab.demobackendspringboot.model.Product;
import cl.pablosilvab.demobackendspringboot.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<Response<List<Product>>> getAll() {
        Response<List<Product>> response = new Response<>();
        response.setData(productService.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Product>> get(@PathVariable String id) {
        Response<Product> response = new Response<>();
        try {
            long idValue = Long.parseLong(id);
            Optional<Product> foundProjectOpt = productService.find(idValue);
            if (foundProjectOpt.isPresent()) {
                Product foundProject = foundProjectOpt.get();
                response.setData(foundProject);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.addErrorMsgToResponse("There are no products registered with the number=" + id);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            response.addErrorMsgToResponse("There are no products registered with value=" + id);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Response<Product>> create(@RequestBody Product project) {
        Response<Product> response = new Response<>();
        Product product = productService.create(project);
        response.setData(product);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Product>> update(@RequestBody Product product, @PathVariable String id) {
        Response<Product> response = new Response<>();
        try {
            long idValue = Long.parseLong(id);
            Optional<Product> updatedProjectOpt = productService.update(idValue, product);
            if (updatedProjectOpt.isPresent()) {
                Product updatedProject = updatedProjectOpt.get();
                response.setData(updatedProject);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.addErrorMsgToResponse("There are no products registered with the number=" + id);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            response.addErrorMsgToResponse("There are no products registered with the value=" + id);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/purchase")
    public ResponseEntity<Response<Product>> updateStock(@PathVariable String id, @RequestBody long quantity) throws NoStockException {
        Response<Product> response = new Response<>();
        try {
            long idValue = Long.parseLong(id);
            Optional<Product> productOpt = productService.find(idValue);
            if (productOpt.isPresent()) {
                Product product = productOpt.get();
                response.setData(productService.minusStock(product, quantity));
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.addErrorMsgToResponse("There are no products registered with the number=" + id);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            response.addErrorMsgToResponse("There are no products registered with the number=" + id);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (NoStockException e) {
            response.addErrorMsgToResponse(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
