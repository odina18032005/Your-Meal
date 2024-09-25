package uz.pdp.website_yourmeal.dto;

import org.mapstruct.ap.internal.util.IgnoreJRERequirement;
import org.springframework.web.multipart.MultipartFile;

public record CategoryDto (
        String title
){
}
