package basic.com.api.v1.mapper;

import basic.com.api.v1.model.CategoryDTO;
import basic.com.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    //for mapping between different variable names @Mapping(source = "numberOfSeats", target = "seatCount")
    @Mapping(target = "url", ignore = true)
    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
