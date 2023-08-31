package onlinelibrary.books.service;

import onlinelibrary.books.domain.Genre;

import java.util.List;
import java.util.Set;

public interface GenreService {
    List<Genre> getAll();
    Set<Genre> getAllById(List<Long> ids);
}
