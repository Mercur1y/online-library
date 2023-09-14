package onlinelibrary.books.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Book;
import onlinelibrary.books.dto.BookDto;
import onlinelibrary.books.repo.BookRepository;
import onlinelibrary.books.service.BookService;
import onlinelibrary.common.service.MapperService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final MapperService mapperService;

    @Override
    public void create(BookDto.Request.ToCreate bookDto) {
        repository.save(mapperService.map(bookDto, Book.class));
    }

    @Override
    public Book get(long id) throws NullPointerException {
        Optional<Book> book = repository.findById(id);
        return book.orElseThrow(() -> new NullPointerException("Некорректный id"));
    }

    @Override
    public void update(BookDto.Request.ToEdit bookDto, Long id) {
        Book bookToUpdate = get(id);
        bookToUpdate.setDescription(bookDto.getDescription());
        bookToUpdate.setPrice(bookDto.getPrice());
        repository.save(bookToUpdate);
    }

    @Override
    public void delete(long id) {
        repository.delete(get(id));
    }

    @Override
    public List<BookDto.Response.ForBookGrid> getAll() {
        return mapperService.mapList(repository.findAll(), BookDto.Response.ForBookGrid.class);
    }

    @Override
    public List<BookDto.Response.ForBookGrid> getAllByPage(PageRequest pr) {
        return mapperService.mapList(repository.findAll(pr).getContent(), BookDto.Response.ForBookGrid.class);
    }
}
