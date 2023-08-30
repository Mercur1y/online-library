package onlinelibrary.books.service;

import onlinelibrary.books.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    List<Genre> getAllById(List<Long> ids);
}
