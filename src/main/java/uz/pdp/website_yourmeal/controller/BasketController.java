package uz.pdp.website_yourmeal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.website_yourmeal.dto.BasketItemDto;
import uz.pdp.website_yourmeal.model.Basket;
import uz.pdp.website_yourmeal.model.BasketItem;
import uz.pdp.website_yourmeal.model.Product;
import uz.pdp.website_yourmeal.repository.BasketRepository;
import uz.pdp.website_yourmeal.repository.ProductRepository;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    public BasketController(BasketRepository basketRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/addItem/{basketId}")
    public ResponseEntity<Basket> addItem(@RequestBody BasketItemDto basketItemDto) {
        Basket basket = basketRepository.findById(basketItemDto.basketId())
                .orElseThrow(() -> new RuntimeException("Basket not found"));
        Product product = productRepository.findById(basketItemDto.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        BasketItem item = new BasketItem(product, basketItemDto.quantity());
        basket.addItem(item);
        basketRepository.save(basket);
        return ResponseEntity.ok(basket);
    }

    @PostMapping("/getById()/{basketId}")
    public ResponseEntity<Basket> getById(@PathVariable("basketId") String id){
        Basket basket = basketRepository.findById(id).get();
        return ResponseEntity.ok(basket);
    }

    @DeleteMapping("/deleteById()/{basketId}")
    public void deleteById(@PathVariable("basketId") String id){
        basketRepository.deleteById(id);
    }

    @DeleteMapping("/deleteItem/{basketId}")
    public void deteleItem(@PathVariable String basketId, @RequestParam String itemId) {
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found"));
        BasketItem item = basket.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found"));

        basket.removeItem(item);
        basketRepository.save(basket);
    }
}


