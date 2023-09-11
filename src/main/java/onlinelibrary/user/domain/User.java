package onlinelibrary.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private int active;
    @Column(unique = true, nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;
}
