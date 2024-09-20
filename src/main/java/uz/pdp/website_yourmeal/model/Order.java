package uz.pdp.website_yourmeal.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order")
@Getter
@Setter
@NoArgsConstructor
public class Order extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basketId", referencedColumnName = "id")
    private Basket basket;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;

    @Builder
    public Order(User user, Basket basket, Address address) {
        this.user = user;
        this.basket = basket;
        this.address = address;
    }
}
