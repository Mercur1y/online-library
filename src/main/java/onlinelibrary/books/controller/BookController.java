package onlinelibrary.books.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Author;
import onlinelibrary.books.domain.Book;
import onlinelibrary.books.service.BookService;
import onlinelibrary.common.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private BookService bookService;
    private final ObjectMapper objectMapper;

    private final UtilService utilService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Book createBook(@RequestBody Map<String, Object> content) {
        Book book = objectMapper.convertValue(content, Book.class);
        List<Integer> idsInt = (List<Integer>) content.get("genreIds-inputEl");
        Long authorId = Long.valueOf(content.get("authorId-inputEl").toString());
        return bookService.create(book, idsInt, authorId);
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.PUT)
    public void editBook(@PathVariable long id,
                         @RequestBody Book book) {
        bookService.update(book);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable long id) {
        bookService.delete(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, Object> allBooks(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "limit", required = false) Integer limit) {
        Map<String, Object> response = new HashMap<>();

        if (page != null && limit != null) {
            response.put("total", utilService.countRows(Book.class));
            response.put("data", bookService.getAllByPage(PageRequest.of(page - 1, limit)).getContent());
        } else response.put("data", bookService.getAll());
        return response;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}
