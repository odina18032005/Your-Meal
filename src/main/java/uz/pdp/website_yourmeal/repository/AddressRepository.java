package uz.pdp.website_yourmeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.website_yourmeal.model.Address;

public interface AddressRepository extends JpaRepository<Address, String> {
}
