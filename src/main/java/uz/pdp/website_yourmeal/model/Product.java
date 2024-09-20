package uz.pdp.website_yourmeal.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity{
    private String title;
    private String desc;
    private Integer weight;
    private Integer price;
    @Column(columnDefinition = "TEXT[]")
    private List<String> compound;
    private Integer calories;
    private String categoryId;

    @Builder
    public Product(String title, String desc, Integer weight, Integer price, List<String> compound, Integer calories, String categoryId) {
        this.title = title;
        this.desc = desc;
        this.weight = weight;
        this.price = price;
        this.compound = compound;
        this.calories = calories;
        this.categoryId = categoryId;
    }
}
