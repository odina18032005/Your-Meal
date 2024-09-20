package uz.pdp.website_yourmeal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "basket_item")
@Getter
@Setter
@NoArgsConstructor
public class BasketItem extends BaseEntity{
    private String basketId;
    private String productId;
    private Integer quantity;
    private Integer price;
    private Integer total;

    @Builder
    public BasketItem(String basketId, String productId, Integer quantity, Integer price, Integer total) {
        this.basketId = basketId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }
}
