package onlinelibrary.books.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public enum AuthorDto {;
    private interface Id { Long getId(); }
    private interface Fio { String getFio(); }
    private interface Description { String getDescription(); }

    @Getter @Setter @NoArgsConstructor
    public static class Default implements Fio, Description {
        String fio;
        String description;
    }

    @Getter @Setter @NoArgsConstructor
    public static class ForAuthorGrid implements Id, Fio, Description {
        Long id;
        String fio;
        String description;
    }

    @Getter @Setter @NoArgsConstructor
    public static class ForBookGrid implements Id, Fio {
        Long id;
        String fio;
    }
}
