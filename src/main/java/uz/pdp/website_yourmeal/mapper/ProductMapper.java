package uz.pdp.website_yourmeal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.pdp.website_yourmeal.dto.ProductDto;
import uz.pdp.website_yourmeal.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "image", ignore = true)
    ProductDto toDto(Product product);
    @Mapping(target = "image", ignore = true)
    Product toEntity(ProductDto productDto);
}
