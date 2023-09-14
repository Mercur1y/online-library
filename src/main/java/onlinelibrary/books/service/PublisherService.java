package onlinelibrary.books.service;

import onlinelibrary.books.domain.Publisher;
import onlinelibrary.books.dto.PublisherDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PublisherService {
    void create(PublisherDto.NameOnly publisher);
    Publisher get(long id) throws Exception;
    void update(PublisherDto.NameOnly publisher, Long id);
    void delete(long id);
    List<PublisherDto.Default> getAll();
    List<PublisherDto.Default> getAllByPage(PageRequest pr);
}
