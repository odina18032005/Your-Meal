package uz.pdp.website_yourmeal.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.website_yourmeal.dto.CategoryDto;
import uz.pdp.website_yourmeal.mapper.CategoryMapper;
import uz.pdp.website_yourmeal.model.Category;
import uz.pdp.website_yourmeal.repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Category> create(CategoryDto categoryDto){
        Category entity = Mappers.getMapper(CategoryMapper.class).toEntity(categoryDto);
        Category save = categoryRepository.save(entity);
        return ResponseEntity.ok(save);
    }

    @PostMapping("/update")
    public ResponseEntity<Category> update(CategoryDto categoryDto){
        Category entity = Mappers.getMapper(CategoryMapper.class).toEntity(categoryDto);
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
