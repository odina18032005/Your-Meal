package uz.pdp.website_yourmeal.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductDto (
        String title,
        MultipartFile image,
        String desc,
        Integer weight,
        Integer price,
        List<String> compound,
        Integer calories,
        String categoryId
){
}
