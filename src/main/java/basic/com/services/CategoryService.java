package basic.com.services;

import basic.com.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    List<CategoryDTO> getCategoryByName(String name);

    CategoryDTO getCategoryById(Long id);

    CategoryDTO createNewCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    void deleteCategoryById(Long id);

}
