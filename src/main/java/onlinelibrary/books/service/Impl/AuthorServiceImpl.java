package onlinelibrary.books.service.Impl;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Author;
import onlinelibrary.books.dto.AuthorDto;
import onlinelibrary.books.repo.AuthorRepository;
import onlinelibrary.books.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author get(long id) throws NullPointerException {
        Optional<Author> author = authorRepository.findById(id);
        return author.orElseThrow(() -> new NullPointerException("Некорректный id"));
    }

    @Override
    public Author update(Author author) {
        Author authorToUp = get(author.getId());
        authorToUp.setFio(author.getFio());
        authorToUp.setDescription(author.getDescription());
        return authorRepository.save(authorToUp);
    }

    @Override
    public void delete(long id) {
        authorRepository.delete(get(id));
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }
}
