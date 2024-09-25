package uz.pdp.website_yourmeal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.pdp.website_yourmeal.dto.CategoryDto;
import uz.pdp.website_yourmeal.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "icon", ignore = true)
    CategoryDto toDto(Category category);
    @Mapping(target = "icon", ignore = true)
    Category toEntity(CategoryDto categoryDto);
}
