package onlinelibrary.books.controller;

import lombok.RequiredArgsConstructor;
import onlinelibrary.books.domain.Publisher;
import onlinelibrary.books.dto.PublisherDto;
import onlinelibrary.books.service.PublisherService;
import onlinelibrary.common.payload.response.MessageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public MessageResponse createPublisher(@RequestBody PublisherDto.NameOnly publisher) {
        publisherService.create(publisher);
        return new MessageResponse("Успешное добавление издателя");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public MessageResponse editPublisher(@PathVariable long id,
                              @RequestBody PublisherDto.NameOnly publisher) {
        publisherService.update(publisher, id);
        return new MessageResponse("Успешное изменение издателя");

    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public MessageResponse deletePublisher(@PathVariable long id) {
        publisherService.delete(id);
        return new MessageResponse("Издатель успешно удален");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PublisherDto.Default> allPublishers(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "limit", required = false) Integer limit) {
        if (page != null && limit != null)
            return publisherService.getAllByPage(PageRequest.of(page - 1, limit));
        else
            return publisherService.getAll();
    }
}
