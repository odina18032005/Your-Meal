package uz.pdp.website_yourmeal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "basket")
@Getter
@Setter
@NoArgsConstructor
public class Basket extends BaseEntity{
    private String userId;
    private Integer total_price;

    @Builder
    public Basket(String userId, Integer total_price) {
        this.userId = userId;
        this.total_price = total_price;
    }
}
