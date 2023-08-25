package onlinelibrary.books.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import onlinelibrary.cart.domain.CartItem;
import onlinelibrary.cart.domain.ListItem;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Lob
    @Column (columnDefinition = "bytea")
    private byte[] content;
    private String name;
    private Integer pageCount;
    private String description;
    @Column (columnDefinition = "bytea")
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @JsonIgnore
    @OneToOne(mappedBy = "book")
    private ListItem listItem;

}
