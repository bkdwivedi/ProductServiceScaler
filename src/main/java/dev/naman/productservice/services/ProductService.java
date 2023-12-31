package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(UUID uuid) throws NotFoundException;
    GenericProductDto getProductById(Long id) throws NotFoundException;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProduct(Long id);
    GenericProductDto deleteProduct(UUID uuid);

    GenericProductDto updateProductById(UUID uuid, GenericProductDto genericProductDto) throws NotFoundException;
    GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) throws NotFoundException;



}
