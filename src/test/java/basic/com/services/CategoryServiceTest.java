package basic.com.services;

import basic.com.api.v1.mapper.CategoryMapper;
import basic.com.api.v1.model.CategoryDTO;
import basic.com.controllers.v1.CategoryController;
import basic.com.domain.Category;
import basic.com.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class CategoryServiceTest {

    CategoryServiceImpl categoryService;

    @Mock
    CategoryRepository categoryRepository;

    List<Category> categories = new ArrayList<>();
    Category fruits;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        categoryService = new CategoryServiceImpl(Mappers.getMapper(CategoryMapper.class), categoryRepository);

        //given

        fruits = new Category("Fruits");
        fruits.setId(1L);

        Category dried = new Category("Dried");
        dried.setId(2L);

        Category fresh = new Category("Fresh");
        fresh.setId(3L);

        categories.add(fruits);
        categories.add(dried);
        categories.add(fresh);
    }

    @Test
    public void getAllCategories() throws Exception {

        //given categories above
        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        //then
        assertEquals(3, categoryDTOS.size());
        assertEquals("Fresh", categoryDTOS.stream().filter(categoryDTO -> categoryDTO.getId().equals(3L)).findFirst().get().getName());

    }

    @Test
    public void getCategoryByName() throws Exception {
        //given categories above
        when(categoryRepository.findByName(anyString())).thenReturn(categories);

        //when
        List<CategoryDTO> fruitsDTO = categoryService.getCategoryByName("Fruits");

        //
        assertEquals(1L, fruitsDTO.get(0).getId());
        assertEquals("Fruits", fruitsDTO.get(0).getName());
        assertEquals(CategoryController.BASE_URL+"/1/description", fruitsDTO.get(0).getUrl());
    }

    @Test
    public void getCategoryById() throws Exception {
        //given categories above
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(fruits));

        //when
        CategoryDTO fruitsDTO = categoryService.getCategoryById(1L);

        //
        assertEquals(1L, fruitsDTO.getId());
        assertEquals("Fruits", fruitsDTO.getName());
        assertEquals(CategoryController.BASE_URL+"/1/description", fruitsDTO.getUrl());
    }

    @Test
    public void createNewCategory() throws Exception {
        CategoryDTO newCategoryDTO = new CategoryDTO();
        newCategoryDTO.setName("Properties");

        Category savedCategory = new Category("Properties");
        savedCategory.setId(6L);

        when(categoryRepository.save(any())).thenReturn(savedCategory);

        //when
        CategoryDTO newDTO = categoryService.createNewCategory(newCategoryDTO);

        assertEquals(6L, newDTO.getId());
        assertEquals("Properties", newDTO.getName());
        assertEquals(CategoryController.BASE_URL+"/6/description", newDTO.getUrl());

    }

    @Test
    public void updateCategory() throws Exception {
        CategoryDTO newCategoryDTO = new CategoryDTO();
        newCategoryDTO.setName("Properties");

        Category savedCategory = new Category("Properties");

        savedCategory.setId(6L);

        when(categoryRepository.save(any())).thenReturn(savedCategory);

        //when
        CategoryDTO newDTO = categoryService.updateCategory(newCategoryDTO);

        assertEquals(6L, newDTO.getId());
        assertEquals("Properties", newDTO.getName());
        assertEquals(CategoryController.BASE_URL+"/6/description", newDTO.getUrl());

    }

    @Test
    void deleteCategoryById() {

        Long id = 1L;

        categoryService.deleteCategoryById(id);

        verify(categoryRepository, times(1)).deleteById(anyLong());



    }
}