package onlinelibrary.books.controller;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Genre;
import onlinelibrary.books.service.GenreService;
import onlinelibrary.common.service.UtilService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/genre")
public class GenreController {

    private final GenreService genreService;
    private final UtilService utilService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.create(genre);
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.PUT)
    public void editGenre(@PathVariable long id,
                             @RequestBody Genre genre) {
        genreService.update(genre);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteGenre(@PathVariable long id) {
        genreService.delete(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, Object> allGenre(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "limit", required = false) Integer limit) {
        Map<String, Object> response = new HashMap<>();

        if(page != null && limit !=null) {
            response.put("total", utilService.countRows(Genre.class));
            response.put("data", genreService.getAllByPage(PageRequest.of(page-1, limit)).getContent());
        } else response.put("data", genreService.getAll());
        return response;
    }
}
