package uz.pdp.website_yourmeal.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.website_yourmeal.converter.ConverterCompout;

import java.time.LocalDateTime;
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
    private String description;
    private Integer weight;
    private Integer price;
    @Convert(converter = ConverterCompout.class)
    private List<String> compound;
    private Integer calories;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;
    @Builder
    public Product(String id, Integer createBy, LocalDateTime createdDate, LocalDateTime updatedDate, Integer updateBy, String title, File image, String description, Integer weight, Integer price, List<String> compound, Integer calories, Category category) {
        super(id, createBy, createdDate, updatedDate, updateBy);
        this.title = title;
        this.image = image;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.compound = compound;
        this.calories = calories;
        this.category = category;
    }
}
