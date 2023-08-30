package onlinelibrary.books.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Genre {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private GenreEnum type;
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="book_genre",
            joinColumns=  @JoinColumn(name="genre_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="book_id", referencedColumnName="id"))
    private List<Book> books;
}
