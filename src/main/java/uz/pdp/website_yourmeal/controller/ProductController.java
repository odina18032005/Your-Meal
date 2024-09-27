package uz.pdp.website_yourmeal.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import uz.pdp.website_yourmeal.mapper.ProductMapper;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.website_yourmeal.dto.CategoryDto;
import uz.pdp.website_yourmeal.dto.ProductDto;
import uz.pdp.website_yourmeal.mapper.CategoryMapper;
import uz.pdp.website_yourmeal.mapper.ProductMapper;
import uz.pdp.website_yourmeal.model.Category;
import uz.pdp.website_yourmeal.model.Product;
import uz.pdp.website_yourmeal.repository.ProductRepository;
import uz.pdp.website_yourmeal.service.S3StorageService;


import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final S3StorageService s3StorageService;
    private final String URL_ICON = "https://yourmealg40bucket.s3.ap-northeast-1.amazonaws.com/public/";

    public ProductController(ProductRepository productRepository, S3StorageService s3StorageService) {
        this.productRepository = productRepository;
        this.s3StorageService = s3StorageService;
    }

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Product> create(ProductDto productDto, @RequestParam("image") MultipartFile image) {
        Product entity = Mappers.getMapper(ProductMapper.class).toEntity(productDto);
        String upload = s3StorageService.uploadToPublic(image);
        String url = URL_ICON + upload;
        entity.setImage(url);
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

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAll(){
        List<Product> all = productRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/getById/{productId}")
    public ResponseEntity<Product> getById(@PathVariable("productId") String id){
        Product product = productRepository.findById(id).get();
        return ResponseEntity.ok(product);
    }
}
