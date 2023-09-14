package onlinelibrary.books.service.Impl;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Author;
import onlinelibrary.books.dto.AuthorDto;
import onlinelibrary.books.repo.AuthorRepository;
import onlinelibrary.books.service.AuthorService;
import onlinelibrary.common.service.MapperService;
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
    private final MapperService mapperService;

    @Override
    public void create(AuthorDto.Default authorDto) {
        repository.save(mapperService.map(authorDto, Author.class));
    }

    @Override
    public Author get(long id) throws NullPointerException {
        Optional<Author> author = repository.findById(id);
        return author.orElseThrow(() -> new NullPointerException("Некорректный id"));
    }

    @Override
    public void update(AuthorDto.Default authorDto, Long id) {
        Author authorToUp = get(id);
        authorToUp.setFio(authorDto.getFio());
        authorToUp.setDescription(authorDto.getDescription());
        repository.save(authorToUp);
    }

    @Override
    public void delete(long id) {
        repository.delete(get(id));
    }

    @Override
    public List<AuthorDto.ForAuthorGrid> getAll() {
        return mapperService.mapList(repository.findAll(), AuthorDto.ForAuthorGrid.class);
    }

    @Override
    public List<AuthorDto.ForAuthorGrid> getAllByPage(PageRequest pr) {
        return mapperService.mapList(repository.findAll(pr).getContent(), AuthorDto.ForAuthorGrid.class);
    }
}
