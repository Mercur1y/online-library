package onlinelibrary.books.controller;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Author;
import onlinelibrary.books.dto.AuthorDto;
import onlinelibrary.books.service.AuthorService;
import onlinelibrary.common.payload.response.MessageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public MessageResponse createAuthor(@RequestBody AuthorDto.Default author) {
        authorService.create(author);
        return new MessageResponse("Успешное добавление автора");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public MessageResponse editAuthor(@PathVariable long id,
                                      @RequestBody AuthorDto.Default author) {
        authorService.update(author, id);
        return new MessageResponse("Успешное изменение автора");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public MessageResponse deleteAuthor(@PathVariable long id) {
        authorService.delete(id);
        return new MessageResponse("Автор успешно удален");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<AuthorDto.ForAuthorGrid> allAuthors(@RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "limit", required = false) Integer limit) {
        if (page != null && limit != null)
            return authorService.getAllByPage(PageRequest.of(page - 1, limit));
        else
            return authorService.getAll();
    }
}
