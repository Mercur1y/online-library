package onlinelibrary.books.service.Impl;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Publisher;
import onlinelibrary.books.repo.PublisherRepository;
import onlinelibrary.books.service.PublisherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublihserServiceImpl implements PublisherService {

    private final PublisherRepository repository;

    @Override
    public Publisher create(Publisher publisher) {
        return repository.save(publisher);
    }

    @Override
    public Publisher get(long id) throws NullPointerException {
        Optional<Publisher> publisher = repository.findById(id);
        return publisher.orElseThrow(() -> new NullPointerException("Некорректный id"));
    }

    @Override
    public Publisher update(Publisher publisher) {
        Publisher publisherToUp = get(publisher.getId());
        publisherToUp.setName(publisher.getName());
        return repository.save(publisherToUp);
    }

    @Override
    public void delete(long id) {
        repository.delete(get(id));
    }

    @Override
    public List<Publisher> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Publisher> getAllByPage(PageRequest pr) {
        return repository.findAll(pr);
    }
}
