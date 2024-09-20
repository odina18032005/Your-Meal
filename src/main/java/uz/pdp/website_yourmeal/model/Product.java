package uz.pdp.website_yourmeal.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;
import uz.pdp.website_yourmeal.converter.ConverterCompout;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity{
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fileId", referencedColumnName = "id")
    private File image;
    private String desc;
    private Integer weight;
    private Integer price;
    @Convert(converter = ConverterCompout.class)
    private List<String> compound;
    private Integer calories;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;

    @Builder
    public Product(String title, File image, String desc, Integer weight, Integer price, List<String> compound, Integer calories, Category category) {
        this.title = title;
        this.image = image;
        this.desc = desc;
        this.weight = weight;
        this.price = price;
        this.compound = compound;
        this.calories = calories;
        this.category = category;
    }
}
