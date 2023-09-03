package onlinelibrary.cart.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import onlinelibrary.books.domain.Book;

import java.util.Date;

@Entity
@Getter @Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Date createdDate;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Book book;
}
