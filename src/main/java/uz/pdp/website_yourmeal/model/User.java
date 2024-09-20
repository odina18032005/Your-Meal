package uz.pdp.website_yourmeal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity{

    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    @Column(columnDefinition ="varchar default 'USER'" )
    private String role;

    @Builder
    public User(String firstName, String lastName, String phone, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }
}
