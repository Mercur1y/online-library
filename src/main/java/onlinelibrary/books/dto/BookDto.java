package onlinelibrary.books.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import onlinelibrary.common.domain.FileInfo;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public enum BookDto {;
    private interface Id { @Positive Long getId(); }
    private interface Name { @NotBlank String getName(); }
    private interface Description { @NotBlank @Length(max = 511) String getDescription(); }
    private interface Image { byte[] getImage(); }
    private interface Author { @NotNull AuthorDto.ForBookGrid getAuthor(); }
    private  interface Genres { List<GenreDto.Default> getGenres(); }
    private interface Publisher { @NotNull PublisherDto.Default getPublisher(); }
    private interface PublishYear { @NotEmpty Integer getPublishYear(); }
    private interface Price { @Positive Double getPrice(); }
    private interface Rate { @Positive Double getRate(); }
    private interface File { FileInfo getFile(); }

    public enum Request {;
        @Getter @Setter @NoArgsConstructor
        public static class ToCreate implements Name, Description, Price, Author, Genres, Publisher, PublishYear, File {
            String name;
            String description;
            Double price;
            AuthorDto.ForBookGrid author;
            List<GenreDto.Default> genres;
            PublisherDto.Default publisher;
            Integer publishYear;
            FileInfo file;
        }

        @Value public static class ToEdit implements Description, Price {
            String description;
            Double price;
        }
    }

    public enum Response {;
        @Getter @Setter @NoArgsConstructor
        public static class ForBookGrid implements Id, Name, Description, Image, Price, Author, Genres, Publisher, PublishYear, File {
            Long id;
            String name;
            String description;
            byte[] image;
            Double price;
            AuthorDto.ForBookGrid author;
            List<GenreDto.TitleOnly> genres;
            PublisherDto.Default publisher;
            Integer publishYear;
            FileInfo file;
        }
    }
}
