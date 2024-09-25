package uz.pdp.website_yourmeal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.pdp.website_yourmeal.dto.CategoryDto;
import uz.pdp.website_yourmeal.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
}
