package uz.pdp.website_yourmeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.website_yourmeal.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByUserId(String userId);
    Optional<Order> findByBasketId(String basketId);
}
