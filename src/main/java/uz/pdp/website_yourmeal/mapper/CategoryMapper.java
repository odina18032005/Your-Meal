package uz.pdp.website_yourmeal.mapper;

import org.mapstruct.Mapper;
import uz.pdp.website_yourmeal.dto.CategoryDto;
import uz.pdp.website_yourmeal.model.Category;

@Mapper
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
}
