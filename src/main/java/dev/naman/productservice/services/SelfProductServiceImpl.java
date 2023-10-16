package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.converter.ProductConvertor;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;


@Service("selfProductServiceImpl")
@Primary
@Repository("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

        @Override
        public GenericProductDto getProductById(java.util.UUID uuid){
            Product product = productRepository.findById(uuid).get();

            return ProductConvertor.getGenericProductDto(product);
        }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return null;
    }


    @Override
            public GenericProductDto createProduct (GenericProductDto genericProductDto){
                Product product = ProductConvertor.getProductDto(genericProductDto);

                productRepository.save(product);

                return ProductConvertor.getGenericProductDto(product);
            }

            @Override
            public List<GenericProductDto> getAllProducts () {
                List<GenericProductDto> genericProductDtos = new ArrayList<>();

                List<Product> products = productRepository.findAll();

                for (Product product : products) {
                    genericProductDtos.add(ProductConvertor.getGenericProductDto(product));
                }

                return genericProductDtos;
            }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }

    @Override
            public GenericProductDto deleteProduct (UUID uuid){

                productRepository.deleteById(uuid);
                return new GenericProductDto();
            }

    @Override
            public GenericProductDto updateProductById (UUID uuid, GenericProductDto genericProductDto) throws
            NotFoundException {
                //Product product = ProductConvertor.getProductDto(genericProductDto);
                Product product = productRepository.getById(uuid);

                product.setTitle(genericProductDto.getTitle());
                product.setDescription(genericProductDto.getDescription());
                Category category = new Category();
                category.setName(genericProductDto.getCategory());
                product.setCategory(category);
                Price price = new Price();
                price.setPrice(genericProductDto.getPrice());
                product.setPrice(price);
                product.setImage(genericProductDto.getImage());

                productRepository.save(product);
                return ProductConvertor.getGenericProductDto(product);
            }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) throws NotFoundException {
        return null;
    }

}