package onlinelibrary.cart.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import onlinelibrary.books.domain.Book;

import java.util.Date;
import java.util.Set;

@Entity
@Getter @Setter
public class ListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Date createdDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(insertable = false, updatable = false)
    private Book book;
}
