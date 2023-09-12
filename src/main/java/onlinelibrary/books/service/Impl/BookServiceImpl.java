package onlinelibrary.books.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Book;
import onlinelibrary.books.repo.AuthorRepository;
import onlinelibrary.books.repo.BookRepository;
import onlinelibrary.books.service.BookService;
import onlinelibrary.books.service.GenreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final GenreService genreService;

    @Override
    @Deprecated
    public Book create(Book book, List<Integer> ids, Long authorId) {
//        List<Long> idsLong = ids.stream().map(Integer::longValue).collect(Collectors.toList());
//        book.setGenres(genreService.getAllById(idsLong));
//        book.setAuthor(authorRepository.findById(authorId).get());
        return repository.save(book);
    }

    @Override
    public Book create(Book book) {
        return repository.save(book);
    }

    @Override
    public Book get(long id) throws NullPointerException {
        Optional<Book> book = repository.findById(id);
        return book.orElseThrow(() -> new NullPointerException("Некорректный id"));
    }

    @Override
    public Book update(Book book) {
        Book bookToUpdate = get(book.getId());
        bookToUpdate.setDescription(book.getDescription());
        bookToUpdate.setGenres(book.getGenres());
        bookToUpdate.setPrice(book.getPrice());
        return repository.save(bookToUpdate);
    }

    @Override
    public void delete(long id) {
        repository.delete(get(id));
    }

    @Override
    public Mono<List<Book>> getAll() {
        return Mono.just(repository.findAll());
    }

    @Override
    public Page<Book> getAllByPage(PageRequest pr) {
        return repository.findAll(pr);
    }
}
