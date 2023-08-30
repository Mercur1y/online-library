package onlinelibrary.books.repo;

import onlinelibrary.books.domain.Genre;
import onlinelibrary.books.domain.GenreEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
