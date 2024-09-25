package uz.pdp.website_yourmeal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class File extends BaseEntity{
    private final String url = "https://yourmealg40bucket.s3.ap-northeast-1.amazonaws.com/public/";
    private String originalFilename;

    @Builder
    public File(String originalFilename) {
        this.originalFilename = originalFilename;
    }
}
