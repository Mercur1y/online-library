package onlinelibrary.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Customer extends User {

    @Column(unique = true, nullable = false)
    private String email;

    private String name;
    private String lastName;

    @Column(nullable = false)
    private int active;
}
