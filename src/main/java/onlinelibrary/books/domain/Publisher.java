package onlinelibrary.books.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Publisher {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    @Override
    public String toString() {return name;}
}
