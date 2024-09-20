package uz.pdp.website_yourmeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.website_yourmeal.model.BasketItem;

import java.util.List;

public interface BasketItemRepository extends JpaRepository<BasketItem, String> {
    List<BasketItem> findAllByBasketId(String basketId);
}
