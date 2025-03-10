package cl.pablosilvab.demobackendspringboot.controller;

import cl.pablosilvab.demobackendspringboot.dto.model.ProductDTO;
import cl.pablosilvab.demobackendspringboot.dto.request.ProductCreateDTO;
import cl.pablosilvab.demobackendspringboot.dto.response.ProductResponseDTO;
import cl.pablosilvab.demobackendspringboot.exception.ProductNotFoundException;
import cl.pablosilvab.demobackendspringboot.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@Tag(name = "Product API", description = "Endpoints for managing products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get all products", description = "Retrieve a list of all available products.")
    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAll(
            @Parameter(description = "Page number (zero-indexed)", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Size of the page", example = "10") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field", example = "name") @RequestParam(defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Page<ProductResponseDTO> productsPage = productService.getAllProducts(pageable);
        return ResponseEntity.ok(productsPage);
    }


    @Operation(summary = "Get a product by its id", description = "Retrieve a specific product by its unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> get(@PathVariable Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Operation(summary = "Create a product", description = "Create a new product with the provided details.")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductCreateDTO productCreateDTO) {
        ProductDTO product = productService.createProduct(productCreateDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }


}
