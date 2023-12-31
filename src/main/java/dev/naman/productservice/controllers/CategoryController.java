package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.GetProductTitlesRequestDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public CategoryDto getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/name")
    public List<GenericProductDto> getProductsByCategory(@RequestBody() String name){
        return categoryService.getProductsByCategory(name);
    }
}
