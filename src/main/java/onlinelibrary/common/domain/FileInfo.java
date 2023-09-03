package onlinelibrary.common.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import onlinelibrary.books.domain.Book;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@ToString
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long size;

    private String key;

    private LocalDateTime uploadDate;

    @JsonBackReference
    @OneToOne
    @JoinColumn
    private Book book;
}
