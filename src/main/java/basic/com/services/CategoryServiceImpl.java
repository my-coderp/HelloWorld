package basic.com.services;

import basic.com.api.v1.mapper.CategoryMapper;
import basic.com.api.v1.model.CategoryDTO;
import basic.com.controllers.v1.CategoryController;
import basic.com.domain.Category;
import basic.com.repositories.CategoryRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryMapper mapper, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories(){

     return categoryRepository.findAll()
             .stream()
             .map(mapper::categoryToCategoryDTO) //for each stream element use method in mapper
             .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getCategoryByName(String name){
        List<Category> foundCategories = categoryRepository.findByName(name);
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        for(Category category:foundCategories){
            CategoryDTO categoryDTO = mapper.categoryToCategoryDTO(category);
            categoryDTO.setUrl(CategoryController.BASE_URL+"/"+category.getId()+"/description");
            categoriesDTO.add(categoryDTO);
        }

        return categoriesDTO;

    }

    @Override
    public CategoryDTO getCategoryById(Long id){

        return categoryRepository.findById(id)
                .map(mapper::categoryToCategoryDTO)
                .map(categoryDTO -> {
                    categoryDTO.setUrl(CategoryController.BASE_URL+"/"+id+"/description");
                    return categoryDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);


    }

    @Override
    public CategoryDTO createNewCategory(CategoryDTO categoryDTO){
        Category category = mapper.categoryDTOToCategory(categoryDTO);

        Category savedCategory = categoryRepository.save(category);

        CategoryDTO returnCategoryDTO = mapper.categoryToCategoryDTO(savedCategory);
        returnCategoryDTO.setUrl(CategoryController.BASE_URL+"/"+savedCategory.getId()+"/description");
        return returnCategoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO){
        Category category = mapper.categoryDTOToCategory(categoryDTO);

        Category savedCategory = categoryRepository.save(category);

        CategoryDTO returnCategoryDTO = mapper.categoryToCategoryDTO(savedCategory);
        returnCategoryDTO.setUrl(CategoryController.BASE_URL+"/"+savedCategory.getId()+"/description");
        return returnCategoryDTO;
    }

    @Override
    public void deleteCategoryById(Long id){
        // missing error handling
        categoryRepository.deleteById(id);
    }
}
