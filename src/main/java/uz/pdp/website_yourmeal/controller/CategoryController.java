package uz.pdp.website_yourmeal.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.website_yourmeal.dto.CategoryDto;
import uz.pdp.website_yourmeal.mapper.CategoryMapper;
import uz.pdp.website_yourmeal.model.Category;
import uz.pdp.website_yourmeal.model.File;
import uz.pdp.website_yourmeal.repository.CategoryRepository;
import uz.pdp.website_yourmeal.repository.FileRepository;
import uz.pdp.website_yourmeal.service.S3StorageService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final S3StorageService s3StorageService;
    private final FileRepository fileRepository;

    public CategoryController(CategoryRepository categoryRepository, S3StorageService s3StorageService, FileRepository fileRepository) {
        this.categoryRepository = categoryRepository;
        this.s3StorageService = s3StorageService;
        this.fileRepository = fileRepository;
    }

    @PostMapping(value = "/create", consumes = {"application/json", MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Category> create(CategoryDto categoryDto, @RequestParam("icon") MultipartFile icon){
        Category entity = Mappers.getMapper(CategoryMapper.class).toEntity(categoryDto);
        File file = fileRepository.save(File.builder().originalFilename(s3StorageService.uploadToPublic(icon)).build());
        entity.setIcon(file);
        Category save = categoryRepository.save(entity);
        return ResponseEntity.ok(save);
    }

    @PostMapping(value = "/update", consumes = {"application/json", MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Category> update(CategoryDto categoryDto, @RequestParam("icon") MultipartFile icon){
        Category entity = Mappers.getMapper(CategoryMapper.class).toEntity(categoryDto);
        File file = fileRepository.save(File.builder().originalFilename(s3StorageService.uploadToPublic(icon)).build());
        entity.setIcon(file);
        Category save = categoryRepository.save(entity);
        return ResponseEntity.ok(save);
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
