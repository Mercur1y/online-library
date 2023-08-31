package onlinelibrary.books.service;
import onlinelibrary.books.domain.Author;
import onlinelibrary.books.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    Author create(Author author);
    Author get(long id) throws Exception;
    Author update(Author author);
    void delete(long id);
    List<Author> getAll();
}
