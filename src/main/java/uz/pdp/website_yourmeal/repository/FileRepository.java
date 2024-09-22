package uz.pdp.website_yourmeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.website_yourmeal.model.File;

public interface FileRepository extends JpaRepository<File, String> {

}
