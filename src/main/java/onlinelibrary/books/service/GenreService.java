package onlinelibrary.books.service;

import onlinelibrary.books.domain.Genre;
import onlinelibrary.books.dto.GenreDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface GenreService {

    void create(GenreDto.TitleOnly genre);
    Genre get(long id) throws Exception;
    void update(GenreDto.TitleOnly genre, Long id);
    void delete(long id);
    List<GenreDto.Default> getAll();
//    Set<Genre> getAllById(List<Long> ids);
    List<GenreDto.Default> getAllByPage(PageRequest pr);
}
