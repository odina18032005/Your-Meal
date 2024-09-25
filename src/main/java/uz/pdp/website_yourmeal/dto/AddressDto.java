package uz.pdp.website_yourmeal.dto;

import uz.pdp.website_yourmeal.model.Address;
import uz.pdp.website_yourmeal.model.Basket;
import uz.pdp.website_yourmeal.model.User;

public record AddressDto(
        Long latitude,
        Long longitude,
        String street,
        String phone,
        String houseNumber
) {
}
