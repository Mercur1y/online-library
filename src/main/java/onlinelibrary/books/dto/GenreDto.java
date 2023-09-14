package onlinelibrary.books.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public enum GenreDto {;
    private interface Id { Long getId(); }
    private interface Title { String getTitle(); }

    @Getter @Setter @NoArgsConstructor
    public static class Default implements Id, Title {
        Long id;
        String title;
    }

    @Getter @Setter @NoArgsConstructor
    public static class TitleOnly implements Title {
        String title;
    }
}
