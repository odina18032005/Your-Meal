package uz.pdp.website_yourmeal.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity{
    private String icon;
    private String title;

    @Builder
    public Category(String icon, String title) {
        this.icon = icon;
        this.title = title;
    }
}
