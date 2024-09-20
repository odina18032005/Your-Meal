package uz.pdp.website_yourmeal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.website_yourmeal.dto.ProductDto;
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

    @PostMapping("/createProduct")
    public ResponseEntity<Product> create(@RequestBody ProductDto productDto){
        Product build = Product.builder()
                .title(productDto.title())
                .description(productDto.desc())
                .price(productDto.price())
                .calories(productDto.calories())
//                .category(productDto.categoryId())
                .weight(productDto.weight())
                .compound(productDto.compound()).build();
        Product save = productRepository.save(build);
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
