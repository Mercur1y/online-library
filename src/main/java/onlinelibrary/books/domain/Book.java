package onlinelibrary.books.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import onlinelibrary.cart.domain.CartItem;
import onlinelibrary.cart.domain.ListItem;

import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column (columnDefinition = "bytea")
    private byte[] content;
    private String name;
    private Integer pageCount;
    private String description;
    @Column (columnDefinition = "bytea")
    private byte[] image;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="book_genre",
            joinColumns=  @JoinColumn(name="book_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="genre_id", referencedColumnName="id"))
    private List<Genre> genres;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Publisher publisher;

    private Integer publishYear;

    private Double price;

    private Double rate;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Estimate> estimates;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<CartItem> cartItems;

    @JsonIgnore
    @OneToOne(mappedBy = "book")
    private ListItem listItem;

}
