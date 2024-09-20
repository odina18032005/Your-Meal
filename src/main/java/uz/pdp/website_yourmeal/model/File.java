package uz.pdp.website_yourmeal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "file")
@Getter
@Setter
@NoArgsConstructor
public class File extends BaseEntity{
    private String name;
    private String originalFilename;
    private String uuid;
    private String contentType;

    @Builder
    public File(String name, String originalFilename, String uuid, String contentType) {
        this.name = name;
        this.originalFilename = originalFilename;
        this.uuid = uuid;
        this.contentType = contentType;
    }
}
