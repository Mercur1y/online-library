package onlinelibrary.common.service;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Book;
import onlinelibrary.books.repo.BookRepository;
import onlinelibrary.common.domain.FileInfo;
import onlinelibrary.common.repo.FileRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final BookRepository bookRepository;

    public final static String SAVE_PATH = "C:/Users/Admin/Documents/IdeaProj/online-library/src/main/resources/static/books_content/";

    public void connectInfoToBook(MultipartFile content, String UUID) {
        byte[] bytesOfImage;
        int pageCount;

        try {
            File file = new File(SAVE_PATH + content.getOriginalFilename());
            content.transferTo(file);
            PDDocument document = PDDocument.load(file);
            pageCount = document.getNumberOfPages();
            bytesOfImage = getByteArrayImage(document);
            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileInfo fileInfo = new FileInfo();
        LocalDateTime uploadDate = LocalDateTime.now();

        fileInfo.setName(content.getOriginalFilename());
        fileInfo.setSize(content.getSize());
        fileInfo.setUploadDate(uploadDate);
        fileInfo.setKey(generateKey(fileInfo.getName(), uploadDate));
        UUID = UUID.replace("\"", "");

        Book book = bookRepository.findBySuuid(UUID);
        book.setImage(bytesOfImage);
        book.setPageCount(pageCount);
        fileInfo.setBook(book);
        fileRepository.save(fileInfo);
    }

    public static byte[] getByteArrayImage(PDDocument document) throws IOException{
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
        ImageIO.write(bim, "jpg", baos);
        return baos.toByteArray();
    }

    private String generateKey(String name, LocalDateTime localDateTime) {
        return DigestUtils.md5Hex(name + localDateTime);
    }
}
