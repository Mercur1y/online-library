package onlinelibrary.books.service;

import onlinelibrary.books.domain.Genre;
import onlinelibrary.books.domain.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PublisherService {
    Publisher create(Publisher publisher);
    Publisher get(long id) throws Exception;
    Publisher update(Publisher publisher);
    void delete(long id);
    List<Publisher> getAll();
    Page<Publisher> getAllByPage(PageRequest pr);
}
