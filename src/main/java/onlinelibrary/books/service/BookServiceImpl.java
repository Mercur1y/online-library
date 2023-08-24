package onlinelibrary.books.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Book;
import onlinelibrary.books.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    public Book create(Book book) {
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
        bookToUpdate.setGenre(book.getGenre());
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
    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
