package uz.pdp.website_yourmeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.website_yourmeal.model.Basket;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, String> {

    Optional<Basket> findByUserId(String userId);
}
