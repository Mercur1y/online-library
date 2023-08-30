package onlinelibrary.books.controller;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Genre;
import onlinelibrary.books.service.GenreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/genre")
public class GenreController {

    private final GenreService genreService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Genre> allGenre() {
        return genreService.getAll();
    }
}
