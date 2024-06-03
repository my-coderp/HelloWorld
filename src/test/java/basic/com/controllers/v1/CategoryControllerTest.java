package basic.com.controllers.v1;

import basic.com.api.v1.model.CategoryDTO;
import basic.com.services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;
    //same as CategoryController categoryController = new CategoryController(categoryService);

    MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }



    @Test
    public void getCategoryById() throws Exception {
        //given
        CategoryDTO dried = new CategoryDTO();
        dried.setId(2L);
        dried.setName("Dried");
        dried.setUrl("/api/v1/categories/dried/description");

        when(categoryService.getCategoryById(anyLong())).thenReturn(dried);

        //when
        mockMvc.perform(get("/api/v1/categories/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Dried")))
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.url", equalTo("/api/v1/categories/dried/description")));
    }


    @Test
    public void getCategoryByName() throws Exception {
        //given
        List<CategoryDTO> categories = new ArrayList<>();
        CategoryDTO dried = new CategoryDTO();
        dried.setId(2L);
        dried.setName("Dried");
        dried.setUrl("/api/v1/categories/dried/description");
        categories.add(dried);

        when(categoryService.getCategoryByName(anyString())).thenReturn(categories);

        //when
        mockMvc.perform(get("/api/v1/categories/find/dried")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.[0].name", equalTo("Dried")))
                .andExpect(jsonPath("$.[0].id", equalTo(2)))
                .andExpect(jsonPath("$.[0].url", equalTo("/api/v1/categories/dried/description")));
    }

    @Test
    public void testListCategories() throws Exception {
        //given
        CategoryDTO dried = new CategoryDTO();
        dried.setId(2L);
        dried.setName("Dried");

        CategoryDTO fresh = new CategoryDTO();
        fresh.setId(3L);
        fresh.setName("Fresh");

        List<CategoryDTO> categories = Arrays.asList(dried, fresh);

        when(categoryService.getAllCategories()).thenReturn(categories);

        //when
        mockMvc.perform(get("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.categories", hasSize(2))));

    }

    @Test
    public void createNewCategory() throws Exception {
        //given
        CategoryDTO properties = new CategoryDTO();
        properties.setId(6L);
        properties.setName("Properties");
        properties.setUrl("/api/v1/categories/Properties/description");

        CategoryDTO createDTO = new CategoryDTO();
        createDTO.setName("Properties");

        when(categoryService.createNewCategory(createDTO)).thenReturn(properties);

        mockMvc.perform(post("/api/v1/categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(mapper.writeValueAsString(createDTO)))  //converts to JSON
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Properties")))
                .andExpect(jsonPath("$.url", equalTo("/api/v1/categories/Properties/description")));
    }
}