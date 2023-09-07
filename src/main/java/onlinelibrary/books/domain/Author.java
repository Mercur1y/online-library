package onlinelibrary.books.domain;

import com.fasterxml.jackson.annotation.*;
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


    @JsonIgnoreProperties(value = {"author"}, allowSetters = true)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

    @Override
    public String toString() {return fio;}
}
