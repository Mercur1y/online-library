package onlinelibrary.books.service.Impl;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Publisher;
import onlinelibrary.books.dto.PublisherDto;
import onlinelibrary.books.repo.PublisherRepository;
import onlinelibrary.books.service.PublisherService;
import onlinelibrary.common.service.MapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublihserServiceImpl implements PublisherService {

    private final PublisherRepository repository;
    private final MapperService mapperService;

    @Override
    public void create(PublisherDto.NameOnly publisherDto) {
        repository.save(mapperService.map(publisherDto, Publisher.class));
    }

    @Override
    public Publisher get(long id) throws NullPointerException {
        Optional<Publisher> publisher = repository.findById(id);
        return publisher.orElseThrow(() -> new NullPointerException("Некорректный id"));
    }

    @Override
    public void update(PublisherDto.NameOnly publisherDto, Long id) {
        Publisher publisherToUp = get(id);
        publisherToUp.setName(publisherDto.getName());
        repository.save(publisherToUp);
    }

    @Override
    public void delete(long id) {
        repository.delete(get(id));
    }

    @Override
    public List<PublisherDto.Default> getAll() {
        return mapperService.mapList(repository.findAll(), PublisherDto.Default.class);
    }

    @Override
    public List<PublisherDto.Default> getAllByPage(PageRequest pr) {
        return mapperService.mapList(repository.findAll(pr).getContent(), PublisherDto.Default.class);
    }
}
