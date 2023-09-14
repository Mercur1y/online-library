package onlinelibrary.books.service;

import onlinelibrary.books.domain.Book;
import onlinelibrary.books.dto.BookDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BookService {
    void create(BookDto.Request.ToCreate book);
    Book get(long id) throws Exception;
    void update(BookDto.Request.ToEdit book, Long id);
    void delete(long id);
    List<BookDto.Response.ForBookGrid> getAll();
    List<BookDto.Response.ForBookGrid> getAllByPage(PageRequest pr);
}
