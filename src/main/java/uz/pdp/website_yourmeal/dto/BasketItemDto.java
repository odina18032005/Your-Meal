package uz.pdp.website_yourmeal.dto;

public record BasketItemDto(
        String basketId,
        String productId,
        Integer quantity
) {
}
