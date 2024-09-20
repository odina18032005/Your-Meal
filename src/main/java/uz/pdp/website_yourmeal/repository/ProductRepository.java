package uz.pdp.website_yourmeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.website_yourmeal.model.Product;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

    List<Product> findByCategoryId(String categoryId);
}
