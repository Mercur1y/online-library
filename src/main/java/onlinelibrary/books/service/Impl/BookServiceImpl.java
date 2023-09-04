package onlinelibrary.books.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Book;
import onlinelibrary.books.repo.AuthorRepository;
import onlinelibrary.books.repo.BookRepository;
import onlinelibrary.books.service.BookService;
import onlinelibrary.books.service.GenreService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreService genreService;

    @Override
    public Book create(Book book, List<Integer> ids, Long authorId) {
        List<Long> idsLong = ids.stream().map(Integer::longValue).collect(Collectors.toList());
        book.setGenres(genreService.getAllById(idsLong));
        book.setAuthor(authorRepository.findById(authorId).get());
        return bookRepository.save(book);
    }

    @Override
    public Book get(long id) throws NullPointerException {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(() -> new NullPointerException("Некорректный id"));
    }

    @Override
    public Book update(Book book) {
        Book bookToUpdate = get(book.getId());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        bookToUpdate.setGenres(book.getGenres());
        bookToUpdate.setName(book.getName());
        bookToUpdate.setImage(book.getImage());
        bookToUpdate.setPrice(book.getPrice());
        bookToUpdate.setPublisher(book.getPublisher());
        bookToUpdate.setPublishYear(book.getPublishYear());
        return bookRepository.save(bookToUpdate);
    }

    @Override
    public void delete(long id) {
        bookRepository.delete(get(id));
    }

    @Override
    public Mono<List<Book>> getAll() {
        return Mono.just(bookRepository.findAll());
    }
}
