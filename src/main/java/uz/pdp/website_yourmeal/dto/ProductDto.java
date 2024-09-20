package uz.pdp.website_yourmeal.dto;

import java.util.List;

public record ProductDto (
        String title,
        String desc,
        Integer weight,
        Integer price,
        List<String> compound,
        Integer calories,
        String categoryId
){
}
