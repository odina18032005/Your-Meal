package uz.pdp.website_yourmeal.controller;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.website_yourmeal.dto.ProductDto;
import uz.pdp.website_yourmeal.mapper.ProductMapper;
import uz.pdp.website_yourmeal.model.Product;
import uz.pdp.website_yourmeal.repository.ProductRepository;


import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody ProductDto productDto){
        Product entity = Mappers.getMapper(ProductMapper.class).toEntity(productDto);
        Product save = productRepository.save(entity);
        return ResponseEntity.ok(save);
    }

    @PostMapping("/update")
    public ResponseEntity<Product> update(@RequestBody ProductDto productDto){
        Product entity = Mappers.getMapper(ProductMapper.class).toEntity(productDto);
        Product save = productRepository.save(entity);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/delete/{productId}")
    public void delete(@PathVariable String productId){
        productRepository.deleteById(productId);
    }

    @GetMapping("/getByCategoryId/{categoryId}")
    public ResponseEntity<List<Product>> getByCategoryId(@PathVariable("categoryId") String categoryId){
        List<Product> byCategoryId = productRepository.findByCategoryId(categoryId);
        return ResponseEntity.ok(byCategoryId);
    }
}
