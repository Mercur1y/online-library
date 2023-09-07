package onlinelibrary.books.controller;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Book;
import onlinelibrary.books.domain.Publisher;
import onlinelibrary.books.service.PublisherService;
import onlinelibrary.common.service.UtilService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/publisher")
public class PublisherController {

    private final PublisherService publisherService;
    private final UtilService utilService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        return publisherService.create(publisher);
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.PUT)
    public void editPublisher(@PathVariable long id,
                                   @RequestBody Publisher publisher) {
        publisherService.update(publisher);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deletePublisher(@PathVariable long id) {
        publisherService.delete(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, Object> allPublishers(@RequestParam(value = "page", required = false) Integer page,
                                             @RequestParam(value = "limit", required = false) Integer limit) {
        Map<String, Object> response = new HashMap<>();

        if (page != null && limit != null) {
            response.put("total", utilService.countRows(Publisher.class));
            response.put("data", publisherService.getAllByPage(PageRequest.of(page - 1, limit)).getContent());
        } else response.put("data", publisherService.getAll());
        return response;
    }
}
