package onlinelibrary.books.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Book;
import onlinelibrary.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private BookService bookService;
    private final ObjectMapper objectMapper;

    @RequestMapping(value = "create/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Book createBook(@RequestBody Book book) {
        return bookService.create(book);
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Book editBook(@PathVariable long id,
                         @RequestBody Map<String, Object> content) {
        Book book = objectMapper.convertValue(content, Book.class);
        return bookService.update(book);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteBook(@PathVariable long id) {
        bookService.delete(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Book> allBooks() {
        return bookService.getAll();
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}
