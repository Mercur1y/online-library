package onlinelibrary.books.controller;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Author;
import onlinelibrary.books.dto.AuthorDto;
import onlinelibrary.books.service.AuthorService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Author createAuthor(@RequestBody Author author) {
        return authorService.create(author);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Author> allBooks() {
        return authorService.getAll();
    }
}
