package onlinelibrary.books.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public enum PublisherDto {;
    private interface Id { Long getId(); }
    private interface Name { String getName(); }

    @Getter @Setter @NoArgsConstructor
    public static class Default implements Id, Name {
        Long id;
        String name;
    }

    @Getter @Setter @NoArgsConstructor
    public static class NameOnly implements Name {
        String name;
    }
}
