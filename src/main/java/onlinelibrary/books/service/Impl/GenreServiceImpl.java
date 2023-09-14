package onlinelibrary.books.service.Impl;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Genre;
import onlinelibrary.books.dto.GenreDto;
import onlinelibrary.books.repo.GenreRepository;
import onlinelibrary.books.service.GenreService;
import onlinelibrary.common.service.MapperService;
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
    private final MapperService mapperService;

    @Override
    public void create(GenreDto.TitleOnly genreDto) {
        repository.save(mapperService.map(genreDto, Genre.class));
    }

    @Override
    public Genre get(long id) throws NullPointerException {
        Optional<Genre> genre = repository.findById(id);
        return genre.orElseThrow(() -> new NullPointerException("Некорректный id"));
    }

    @Override
    public void update(GenreDto.TitleOnly genreDto, Long id) {
        Genre genreToUp = get(id);
        genreToUp.setTitle(genreDto.getTitle());
        repository.save(genreToUp);
    }

    @Override
    public void delete(long id) {
        repository.delete(get(id));
    }

    @Override
    public List<GenreDto.Default> getAll() {
        return mapperService.mapList(repository.findAll(), GenreDto.Default.class);
    }

    @Override
    public List<GenreDto.Default> getAllByPage(PageRequest pr) {
        return mapperService.mapList(repository.findAll(pr).getContent(), GenreDto.Default.class);
    }

}
