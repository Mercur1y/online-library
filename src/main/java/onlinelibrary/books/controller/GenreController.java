package onlinelibrary.books.controller;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Genre;
import onlinelibrary.books.dto.GenreDto;
import onlinelibrary.books.service.GenreService;
import onlinelibrary.common.payload.response.MessageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/genre")
public class GenreController {

    private final GenreService genreService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public MessageResponse createGenre(@RequestBody GenreDto.TitleOnly genre) {
        genreService.create(genre);
        return new MessageResponse("Успешное добавление жанра");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public MessageResponse editGenre(@PathVariable long id,
                          @RequestBody GenreDto.TitleOnly genre) {
        genreService.update(genre, id);
        return new MessageResponse("Успешное изменение жанра");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public MessageResponse deleteGenre(@PathVariable long id) {
        genreService.delete(id);
        return new MessageResponse("Жанр успешно удален");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<GenreDto.Default> allGenre(@RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "limit", required = false) Integer limit) {
        if (page != null && limit != null)
            return genreService.getAllByPage(PageRequest.of(page - 1, limit));
        else
            return genreService.getAll();
    }
}
