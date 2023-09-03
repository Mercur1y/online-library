package onlinelibrary.common.service;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Book;
import onlinelibrary.books.repo.BookRepository;
import onlinelibrary.common.domain.FileInfo;
import onlinelibrary.common.repo.FileRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final BookRepository bookRepository;

    public void connectInfoToBook(MultipartFile content, String UUID) {
        FileInfo file = new FileInfo();
        LocalDateTime uploadDate = LocalDateTime.now();

        file.setName(content.getName());
        file.setSize(content.getSize());
        file.setUploadDate(uploadDate);
        file.setKey(generateKey(file.getName(), uploadDate));
        UUID = UUID.replace("\"", "");
        Book book = bookRepository.findBySuuid(UUID);
        file.setBook(book);
        fileRepository.save(file);
    }

    private String generateKey(String name, LocalDateTime localDateTime) {
        return DigestUtils.md5Hex(name + localDateTime);
    }
}
