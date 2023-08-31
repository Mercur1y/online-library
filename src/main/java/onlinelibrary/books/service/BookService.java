package onlinelibrary.books.service;

import onlinelibrary.books.domain.Book;

import java.util.List;

public interface BookService {
    Book create(Book book, List<Integer> ids, Long authorId);
    Book get(long id) throws Exception;
    Book update(Book book);
    void delete(long id);
    List<Book> getAll();
}
