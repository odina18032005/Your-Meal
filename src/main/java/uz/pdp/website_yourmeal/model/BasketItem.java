package uz.pdp.website_yourmeal.model;

import jakarta.persistence.*;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private Basket basket;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;
    private Integer quantity;
    private Integer price;
    private Integer total;

    @Builder
    public BasketItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice();
        this.total = product.getPrice() * quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        this.price = product.getPrice() * quantity;
    }
}
