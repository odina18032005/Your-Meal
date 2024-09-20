package uz.pdp.website_yourmeal.model;

import jakarta.persistence.*;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    private Integer total_price;

    @Builder
    public Basket(User user, Integer total_price) {
        this.user = user;
        this.total_price = total_price;
    }
}
