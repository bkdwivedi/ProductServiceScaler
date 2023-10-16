package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.ExceptionDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService")ProductService productService) {
        this.productService = productService;
    }


    // GET /products {}
    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts() {
        List<GenericProductDto> productDtos = productService.getAllProducts();
        if (productDtos.isEmpty()) {
            return new ResponseEntity<>(
                    productDtos,
                    HttpStatus.NOT_FOUND
            );
        }

        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for (GenericProductDto gpd : productDtos) {
            genericProductDtos.add(gpd);
        }

        return new ResponseEntity<>(genericProductDtos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        GenericProductDto productDto = productService.getProductById(id);
        if (productDto == null) {
            throw new NotFoundException("Product Doesn't Exist");
        }
        return productDto;
    }

    @GetMapping("/uuid/{uuid}")
    public GenericProductDto getProductByUUID(@PathVariable("uuid")UUID uuid) throws NotFoundException{
        return productService.getProductById(uuid);
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<GenericProductDto> deleteProductByUUID(@PathVariable("uuid") UUID uuid){
        return new ResponseEntity<GenericProductDto>(
                productService.deleteProduct(uuid),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
//        System.out.println(product.name);
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
        public ResponseEntity<GenericProductDto> updateProductById (@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto) throws NotFoundException {
            return new ResponseEntity<GenericProductDto>(
                    productService.updateProductById(id, genericProductDto),
                    HttpStatus.OK
            );
        }

        @PutMapping("/uuid/{uuid}")
        public ResponseEntity<GenericProductDto> updateProductByUUID (@PathVariable("uuid") UUID
        uuid, @RequestBody GenericProductDto genericProductDto) throws NotFoundException {
            return new ResponseEntity<GenericProductDto>(
                    productService.updateProductById(uuid, genericProductDto),
                    HttpStatus.OK
            );
        }

}
