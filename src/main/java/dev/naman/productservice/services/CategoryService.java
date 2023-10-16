package dev.naman.productservice.services;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto getAllCategories();

    List<GenericProductDto> getProductsByCategory(String name);
}
