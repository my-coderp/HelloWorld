package basic.com.controllers.v1;

import basic.com.api.v1.model.CategoryDTO;
import basic.com.api.v1.model.CategoryListDTO;
import basic.com.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    private final CategoryService categoryService;
    public static final String BASE_URL = "/api/v1/categories";

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get a list of categories")
    @GetMapping({"" , "/"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO testListCategories(){
        return new CategoryListDTO(categoryService.getAllCategories());
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryById(@PathVariable Long id){

        return categoryService.getCategoryById(id);
    }

    @GetMapping("/find/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }

    @PostMapping({"" , "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createNewCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.createNewCategory(categoryDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(categoryDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);

    }
}
