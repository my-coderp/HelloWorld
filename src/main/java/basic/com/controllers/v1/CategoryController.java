package basic.com.controllers.v1;

import basic.com.api.v1.model.CategoryDTO;
import basic.com.api.v1.model.CategoryListDTO;
import basic.com.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
public class CategoryController {

    private final CategoryService categoryService;
    public static final String BASE_URL = "/api/v1/categories";

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get a list of categories")
    @GetMapping({BASE_URL , BASE_URL+"/"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO testListCategories(){
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping(BASE_URL+"/time")
    @ResponseStatus(HttpStatus.OK)
    public LocalDateTime getTime(){

        return LocalDateTime.now();
    }

    @GetMapping(BASE_URL+"/env")
    @ResponseStatus(HttpStatus.OK)
    public String getEnv(){

        return System.getenv("TEST");
    }

    @GetMapping(BASE_URL+"/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryById(@PathVariable Long id){

        return categoryService.getCategoryById(id);
    }

    @GetMapping(BASE_URL+"/find/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }

    @PostMapping({BASE_URL , BASE_URL+"/"})
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createNewCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.createNewCategory(categoryDTO);
    }

    @PutMapping({BASE_URL+"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(categoryDTO);
    }

    @DeleteMapping({BASE_URL+"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);

    }
}
