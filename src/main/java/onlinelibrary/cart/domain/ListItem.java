package onlinelibrary.cart.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import onlinelibrary.books.domain.Book;

import java.util.Date;
@Entity
@Getter @Setter
public class ListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerId;
    private Long bookId;
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Book book;
}
