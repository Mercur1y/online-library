package onlinelibrary.books.service.Impl;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Genre;
import onlinelibrary.books.repo.GenreRepository;
import onlinelibrary.books.service.GenreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    public Genre create(Genre genre) {
        return repository.save(genre);
    }

    @Override
    public Genre get(long id) throws NullPointerException {
        Optional<Genre> genre = repository.findById(id);
        return genre.orElseThrow(() -> new NullPointerException("Некорректный id"));
    }

    @Override
    public Genre update(Genre genre) {
        Genre genreToUp = get(genre.getId());
        genreToUp.setTitle(genre.getTitle());
        return repository.save(genreToUp);
    }

    @Override
    public void delete(long id) {
        repository.delete(get(id));
    }

    @Override
    public List<Genre> getAll() {
        return repository.findAll();
    }

    @Override
    public Set<Genre> getAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public Page<Genre> getAllByPage(PageRequest pr) {
        return repository.findAll(pr);
    }

}
