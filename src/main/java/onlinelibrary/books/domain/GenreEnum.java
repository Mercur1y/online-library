package onlinelibrary.books.domain;


public enum GenreEnum {
    CLASSIC ("Классика"),
    DETECTIVE ("Детектив"),
    ADVENTURE ("Приключения"),
    NOVEL ("Роман"),
    FANTASY ("Фэнтези"),
    FANTASTIC ("Фантастика");

    private final String title;

    GenreEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "title='" + title + '\'' +
                '}';
    }
}
