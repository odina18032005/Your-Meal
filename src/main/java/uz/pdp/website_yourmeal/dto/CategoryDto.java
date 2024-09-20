package uz.pdp.website_yourmeal.dto;

import org.springframework.web.multipart.MultipartFile;

public record CategoryDto (
        MultipartFile icon,
        String title
){
}
