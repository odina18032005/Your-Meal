package uz.pdp.website_yourmeal.mapper;

import org.mapstruct.Mapper;
import uz.pdp.website_yourmeal.dto.ProductDto;
import uz.pdp.website_yourmeal.model.Product;

@Mapper
public interface ProductMapper {
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
}
