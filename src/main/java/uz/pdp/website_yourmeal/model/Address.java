package uz.pdp.website_yourmeal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
public class Address extends BaseEntity {
    private Long latitude;
    private Long longitude;
    private String street;
    private String phone;
    private String houseNumber;

    @Builder
    public Address(Long latitude, Long longitude, String street, String phone, String houseNumber) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.street = street;
        this.phone = phone;
        this.houseNumber = houseNumber;
    }
}