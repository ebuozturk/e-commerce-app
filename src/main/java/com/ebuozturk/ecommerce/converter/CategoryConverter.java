package com.ebuozturk.ecommerce.converter;

import com.ebuozturk.ecommerce.dto.CategoryDto;
import com.ebuozturk.ecommerce.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    public CategoryDto convert(Category category){

        return new CategoryDto(category.getId(), category.getName());
    }
    public List<CategoryDto> convert(List<Category> categories){
        return categories.stream().map(this::convert).collect(Collectors.toList());
    }
}
