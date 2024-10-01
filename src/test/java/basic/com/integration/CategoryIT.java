package basic.com.integration;


import basic.com.api.v1.mapper.CategoryMapperImpl;
import basic.com.api.v1.model.CategoryDTO;
import basic.com.bootstrap.Bootstrap;

import basic.com.repositories.CategoryRepository;
import basic.com.services.CategoryService;
import basic.com.services.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@EnabledIf(value = "#{!environment.getActiveProfiles()[0] == 'production'}", loadContext = true)
@DataJpaTest
public class CategoryIT {

    @Autowired
    CategoryRepository categoryRepository;

    CategoryService categoryService;

    Long categoryCount;


    @BeforeEach
    public void setUp() throws Exception {

        //load data
        Bootstrap bootstrap = new Bootstrap(categoryRepository);
        bootstrap.run();

        categoryCount = (long) categoryRepository.findAll().size();

        System.out.println("loading customer data. Number of elements: " + categoryCount);

        categoryService = new CategoryServiceImpl(new CategoryMapperImpl(), categoryRepository);
    }

    @Test
    public void createAndUpdateThenDelete() throws Exception {
        CategoryDTO newCategoryDTO = new CategoryDTO();
        newCategoryDTO.setName("Properties");

        CategoryDTO newDTO = categoryService.createNewCategory(newCategoryDTO);
        Long id = newDTO.getId();

        assertEquals("Properties", newDTO.getName());
        assertEquals("/api/v1/categories/"+id+"/description", newDTO.getUrl());
        assertEquals(categoryCount+1, categoryRepository.findAll().size());

        newDTO.setName("Updated");

        CategoryDTO updatedCategoryDTO = categoryService.updateCategory(newDTO);

        assertEquals(id, updatedCategoryDTO.getId());
        assertEquals("Updated", updatedCategoryDTO.getName());
        assertEquals("/api/v1/categories/"+id+"/description", updatedCategoryDTO.getUrl());

        categoryService.deleteCategoryById(id);
        assertEquals(categoryCount, categoryRepository.findAll().size());

        CategoryDTO deletedDTO = new CategoryDTO();
        try {
            deletedDTO = categoryService.getCategoryById(id);
        } catch (Exception e) {
            System.out.println("object not found as was expected in this test " + e.getMessage());
        }
        assertNull(deletedDTO.getId());
    }


}
