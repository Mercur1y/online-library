package onlinelibrary.books.controller;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Author;
import onlinelibrary.books.service.AuthorService;
import onlinelibrary.common.service.UtilService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/author")
@RequiredArgsConstructor
//todo: refactor api endpoints according to the commonly used practice. e.g. https://restfulapi.net/resource-naming/
// see BookController.java and BookController.js
public class AuthorController {

    private final AuthorService authorService;
    private final UtilService utilService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Author createAuthor(@RequestBody Author author) {
        return authorService.create(author);
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.PUT)
    public void editAuthor(@PathVariable long id,
                             @RequestBody Author author) {
        authorService.update(author);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteAuthor(@PathVariable long id) {
        authorService.delete(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    //TODO: use spring pagination instead
    public Map<String, Object> allAuthors(@RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "limit", required = false) Integer limit) {
        Map<String, Object> response = new HashMap<>();

        if (page != null && limit != null) {
            response.put("total", utilService.countRows(Author.class));
            response.put("data", authorService.getAllByPage(PageRequest.of(page - 1, limit)).getContent());
        } else response.put("data", authorService.getAll());
        return response;
    }
}
