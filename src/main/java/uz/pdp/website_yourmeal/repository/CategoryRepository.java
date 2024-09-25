package uz.pdp.website_yourmeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.website_yourmeal.model.Category;


import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findById(String id);
}
