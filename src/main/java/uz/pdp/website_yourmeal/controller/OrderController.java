package uz.pdp.website_yourmeal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.website_yourmeal.repository.AddressRepository;
import uz.pdp.website_yourmeal.repository.BasketItemRepository;
import uz.pdp.website_yourmeal.repository.OrderRepository;
import uz.pdp.website_yourmeal.repository.ProductRepository;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final BasketController basketController;
    private final BasketItemRepository basketItemRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;

    public OrderController(OrderRepository orderRepository, BasketController basketController, BasketItemRepository basketItemRepository, ProductRepository productRepository, AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.basketController = basketController;
        this.basketItemRepository = basketItemRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
    }
}
