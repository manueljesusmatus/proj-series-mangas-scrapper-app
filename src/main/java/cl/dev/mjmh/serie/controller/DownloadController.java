package cl.dev.mjmh.serie.controller;

import cl.dev.mjmh.serie.model.entity.dto.SerieDto;
import cl.dev.mjmh.serie.service.DownloadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/download")
public class DownloadController {

    private final DownloadService downloadService;

    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    @GetMapping
    public List<SerieDto> loadSeriesFromWeb() {
        return downloadService.downloadSeries();
    }
}
