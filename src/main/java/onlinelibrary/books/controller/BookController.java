package onlinelibrary.books.controller;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.dto.BookDto;
import onlinelibrary.books.service.BookService;
import onlinelibrary.common.payload.response.MessageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public MessageResponse createBook(@RequestBody BookDto.Request.ToCreate book) {
        bookService.create(book);
        return new MessageResponse("Успешное добавление книги");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public MessageResponse editBook(@PathVariable long id,
                         @RequestBody BookDto.Request.ToEdit book) {
        bookService.update(book, id);
        return new MessageResponse("Успешное изменение книги");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public MessageResponse deleteBook(@PathVariable long id) {
        bookService.delete(id);
        return new MessageResponse("Книга успешно удалена");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<BookDto.Response.ForBookGrid> allBooks(@RequestParam(value = "page", required = false) Integer page,
                                                       @RequestParam(value = "limit", required = false) Integer limit) {
        if (page != null && limit != null)
            return bookService.getAllByPage(PageRequest.of(page - 1, limit));
        else
            return bookService.getAll();
    }
}
