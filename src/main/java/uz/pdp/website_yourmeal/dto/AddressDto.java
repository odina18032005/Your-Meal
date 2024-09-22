package uz.pdp.website_yourmeal.dto;

public record AddressDto(
        Long latitude,
        Long longitude,
        String street,
        String phone,
        String houseNumber
) {
}
