package onlinelibrary.books.dto;

import lombok.Data;
import onlinelibrary.books.domain.Publisher;
import onlinelibrary.common.domain.FileInfo;

@Data
public class BookGridDto {
    private Long id;
    private String name;
    private String suuid;
    private Integer pageCount;
    private String description;
    private byte[] image;
    private AuthorGridDto author;
    private Publisher publisher;
    private Integer publishYear;
    private Double price;
    private Double rate;
    private FileInfo file;
}
