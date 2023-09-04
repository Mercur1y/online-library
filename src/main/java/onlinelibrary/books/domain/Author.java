package onlinelibrary.books.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Author {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String fio;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Book> books;

    @Override
    public String toString() {return fio;}
}
