package onlinelibrary.books.service.Impl;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Author;
import onlinelibrary.books.repo.AuthorRepository;
import onlinelibrary.books.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public Author create(Author author) {
        return repository.save(author);
    }

    @Override
    public Author get(long id) throws NullPointerException {
        Optional<Author> author = repository.findById(id);
        return author.orElseThrow(() -> new NullPointerException("Некорректный id"));
    }

    @Override
    public Author update(Author author) {
        Author authorToUp = get(author.getId());
        authorToUp.setFio(author.getFio());
        authorToUp.setDescription(author.getDescription());
        return repository.save(authorToUp);
    }

    @Override
    public void delete(long id) {
        repository.delete(get(id));
    }

    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Author> getAllByPage(PageRequest pr) {
        return repository.findAll(pr);
    }
}
