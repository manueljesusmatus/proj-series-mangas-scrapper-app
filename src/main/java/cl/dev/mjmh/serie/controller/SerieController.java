package cl.dev.mjmh.serie.controller;

import cl.dev.mjmh.serie.model.entity.dto.RequestSerie;
import cl.dev.mjmh.serie.model.entity.dto.SerieDto;
import cl.dev.mjmh.serie.service.SerieService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping
    public List<SerieDto> getAllSeries() {
        return serieService.readAllSeries();
    }

    @GetMapping("/page")
    public Page<SerieDto> getPageSerie(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize
    ) {
        pageSize = (pageSize > 20 || pageSize < 10) ? 10 : pageSize;
        return serieService.readByPage(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public SerieDto getSerieById(@PathVariable Long id) {
        return serieService.readSerieById(id);
    }

    @PutMapping("/{id}")
    public SerieDto putSerieById(@PathVariable Long id) {
        return serieService.updateSerie(id);
    }

    @PostMapping
    public SerieDto postSerie(@RequestBody RequestSerie serie) {
        return serieService.createSerie(serie);
    }

}
