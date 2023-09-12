package onlinelibrary.books.repo;

import onlinelibrary.books.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Override
//    @Query("select b " +
//            "from Book b " +
//            "left join b.file " +
//            "left join b.author " +
//            "left join b.genres")
    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"author", "publisher", "genres", "estimates", "file"}
    )
    Page<Book> findAll(Pageable pageable);

    Book findBySuuid(String UUID);
}
