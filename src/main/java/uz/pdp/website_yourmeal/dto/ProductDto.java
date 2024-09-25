package uz.pdp.website_yourmeal.dto;

import org.mapstruct.ap.internal.util.IgnoreJRERequirement;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.website_yourmeal.model.File;

import java.util.List;

public record ProductDto (
        String id,
        String title,
        String desc,
        File image,
        Integer weight,
        Integer price,
        List<String> compound,
        Integer calories,
        String categoryId
){
}
