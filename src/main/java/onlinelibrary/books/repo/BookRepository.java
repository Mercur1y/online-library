package onlinelibrary.books.repo;

import onlinelibrary.books.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Override
    @Query("select b from Book b left join fetch b.listItem")
    List<Book> findAll();
}
