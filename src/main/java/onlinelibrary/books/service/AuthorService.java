package onlinelibrary.books.service;
import onlinelibrary.books.domain.Author;
import onlinelibrary.books.domain.Genre;
import onlinelibrary.books.dto.AuthorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AuthorService {
    void create(AuthorDto.Default author);
    Author get(long id) throws Exception;
    void update(AuthorDto.Default author, Long id);
    void delete(long id);
    List<AuthorDto.ForAuthorGrid> getAll();
    List<AuthorDto.ForAuthorGrid> getAllByPage(PageRequest pr);
}
