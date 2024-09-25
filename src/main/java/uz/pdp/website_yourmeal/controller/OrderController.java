package uz.pdp.website_yourmeal.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uz.pdp.website_yourmeal.dto.AddressDto;
import uz.pdp.website_yourmeal.mapper.AddressMapper;
import uz.pdp.website_yourmeal.model.Address;
import uz.pdp.website_yourmeal.model.Basket;
import uz.pdp.website_yourmeal.model.Order;
import uz.pdp.website_yourmeal.model.User;
import uz.pdp.website_yourmeal.repository.*;
import uz.pdp.website_yourmeal.utils.SecurityUtils;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;
    private final BasketRepository basketRepository;
    private final SecurityUtils securityUtils;
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, ProductRepository productRepository, AddressRepository addressRepository, BasketRepository basketRepository, SecurityUtils securityUtils, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
        this.basketRepository = basketRepository;
        this.securityUtils = securityUtils;
        this.userRepository = userRepository;
    }

    @PostMapping("/create/{basketId}")
    public ResponseEntity<Order> create(@PathVariable("basketId") String basketId, @RequestBody AddressDto addressDto){
        Basket basket = basketRepository.findById(basketId).get();
        Address entity = Mappers.getMapper(AddressMapper.class).toEntity(addressDto);
        Address address = addressRepository.save(entity);
        User user = userRepository.findById(securityUtils.getUser()).get();
        Order order = orderRepository.save(Order.builder().address(address).basket(basket).user(user).build());
        return ResponseEntity.ok(order);
    }
}
