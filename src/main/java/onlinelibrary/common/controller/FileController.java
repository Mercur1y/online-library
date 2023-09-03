package onlinelibrary.common.controller;

import lombok.RequiredArgsConstructor;
import onlinelibrary.common.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/content")
    public void uploadContent(@RequestParam("file") MultipartFile file,
                              @RequestParam("uuid") String uuid) {
        fileService.connectInfoToBook(file, uuid);
    }
}
