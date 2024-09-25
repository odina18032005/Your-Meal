package uz.pdp.website_yourmeal.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.website_yourmeal.converter.ConverterCompout;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "basket")
@Getter
@Setter
@NoArgsConstructor
public class Basket extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketItem> items;
    private Integer total_price;
    private boolean isActive;

    @Builder
    public Basket(User user, Integer total_price, boolean isActive, List<BasketItem> items) {
        this.user = user;
        this.total_price = total_price;
        this.isActive = isActive;
        this.items = items;
    }

    public void addItem(BasketItem item) {
        items.add(item);
        item.setBasket(this);
        updateTotalPrice();
    }

    public void removeItem(BasketItem item) {
        items.remove(item);
        item.setBasket(null);
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        total_price = items.stream()
                .mapToInt(BasketItem::getPrice)
                .sum();
    }
}
