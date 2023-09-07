package onlinelibrary.books.service;

import onlinelibrary.books.domain.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Set;

public interface GenreService {

    Genre create(Genre genre);
    Genre get(long id) throws Exception;
    Genre update(Genre genre);
    void delete(long id);
    List<Genre> getAll();
    Set<Genre> getAllById(List<Long> ids);
    Page<Genre> getAllByPage(PageRequest pr);
}
