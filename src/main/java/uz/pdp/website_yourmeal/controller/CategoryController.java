package uz.pdp.website_yourmeal.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.website_yourmeal.dto.CategoryDto;
import uz.pdp.website_yourmeal.mapper.CategoryMapper;
import uz.pdp.website_yourmeal.model.Category;
import uz.pdp.website_yourmeal.repository.CategoryRepository;
import uz.pdp.website_yourmeal.service.S3StorageService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final String URL_ICON = "https://yourmealg40bucket.s3.ap-northeast-1.amazonaws.com/public/";
    private final CategoryRepository categoryRepository;
    private final S3StorageService s3StorageService;

    public CategoryController(CategoryRepository categoryRepository, S3StorageService s3StorageService) {
        this.categoryRepository = categoryRepository;
        this.s3StorageService = s3StorageService;
    }

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Category> create(CategoryDto categoryDto, @RequestParam("icon") MultipartFile icon) {
        Category entity = Mappers.getMapper(CategoryMapper.class).toEntity(categoryDto);
        String upload = s3StorageService.uploadToPublic(icon);
        String url = URL_ICON + upload;
        entity.setIcon(url);
        Category savedCategory = categoryRepository.save(entity);
        return ResponseEntity.ok(savedCategory);
    }


    @GetMapping("/getById/{categoryId}")
    public ResponseEntity<Category> getById(@PathVariable("categoryId") String id){
        Category category = categoryRepository.findById(id).get();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAll(){
        List<Category> all = categoryRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @DeleteMapping("/deleteById/{categoryId}")
    public void deleteById(@PathVariable("categoryId") String id){
        categoryRepository.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        categoryRepository.deleteAll();
    }
}
