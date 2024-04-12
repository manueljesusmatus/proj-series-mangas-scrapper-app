package cl.dev.mjmh.serie.controller;

import cl.dev.mjmh.serie.model.entity.dto.WebPageDto;
import cl.dev.mjmh.serie.service.WebPageService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/webpages")
public class WebPageController {

    private final WebPageService webPageService;

    public WebPageController(WebPageService webPageService) {
        this.webPageService = webPageService;
    }

    @GetMapping
    public List<WebPageDto> getAll() {
        return webPageService.readAll();
    }

    @GetMapping("/page")
    public Page<WebPageDto> getPage(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        pageSize = (pageSize > 20 || pageSize < 10) ? 10 : pageSize;
        return webPageService.readByPage(pageNumber, pageSize);
    }

    @GetMapping("/:id")
    public WebPageDto getById(@PathVariable Long id) {
        return webPageService.readById(id);
    }

    @GetMapping("/last")
    public String getLast() {
        return webPageService.readLast();
    }

    @GetMapping("/date")

    public List<WebPageDto> getAtDay(@RequestParam String fecha) {
        return webPageService.findAtDay(fecha);
    }

    @PostMapping
    public WebPageDto postWebPage(@RequestBody String content) {
        return webPageService.create(content);
    }

}
