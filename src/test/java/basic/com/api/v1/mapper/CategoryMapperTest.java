package basic.com.api.v1.mapper;

import basic.com.api.v1.model.CategoryDTO;
import basic.com.domain.Category;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


class CategoryMapperTest {

    private final CategoryMapper mapper
            = Mappers.getMapper(CategoryMapper.class);


    @Test
    public void categoryToCategoryDTO() throws Exception{

        //given
        String name = "Nuts";
        Category category = new Category(name);


        Long id = 1L;
        category.setId(id);

        //when
        CategoryDTO categoryDTO = mapper.categoryToCategoryDTO(category);

        //then

        assertNotNull(categoryDTO);

        assertEquals(name, categoryDTO.getName());
        assertEquals(id, categoryDTO.getId());
    }

}