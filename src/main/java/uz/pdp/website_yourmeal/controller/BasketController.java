package uz.pdp.website_yourmeal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.website_yourmeal.model.Basket;
import uz.pdp.website_yourmeal.repository.BasketItemRepository;
import uz.pdp.website_yourmeal.repository.ProductRepository;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketController basketController;
    private final BasketItemRepository basketItemRepository;
    private final ProductRepository productRepository;

    public BasketController(BasketController basketController, BasketItemRepository basketItemRepository, ProductRepository productRepository) {
        this.basketController = basketController;
        this.basketItemRepository = basketItemRepository;
        this.productRepository = productRepository;
    }
}
