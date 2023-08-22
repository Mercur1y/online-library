package onlinelibrary.books.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import onlinelibrary.cart.domain.CartItem;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Lob
    @Column (updatable = false)
    private byte[] content;
    private Integer pageCount;
    private String description;
    private byte[] image;

    @ManyToOne
    @JoinColumn
    private Genre genre;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToOne
    @JoinColumn
    private Publisher publisher;

    private Integer publishYear;

    private Double price;

    private Double rate;

    @OneToMany(mappedBy = "book")
    private List<Estimate> estimates;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<CartItem> cartItems;
}
