package onlinelibrary.books.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import onlinelibrary.common.domain.FileInfo;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String suuid;
    private Integer pageCount;
    @Column(length = 511)
    private String description;
    @Column (columnDefinition = "bytea")
    private byte[] image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="book_genre",
            joinColumns=  @JoinColumn(name="book_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="genre_id", referencedColumnName="id"))
    private Set<Genre> genres = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Publisher publisher;

    private Integer publishYear;

    private Double price;

    private Double rate;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<Estimate> estimates;

    @JsonManagedReference
    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY)
    private FileInfo file;

}
