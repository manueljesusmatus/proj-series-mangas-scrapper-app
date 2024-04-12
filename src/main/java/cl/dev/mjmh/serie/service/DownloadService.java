package cl.dev.mjmh.serie.service;

import cl.dev.mjmh.serie.model.entity.dto.SerieDto;

import java.util.List;

public interface DownloadService {

    List<SerieDto> downloadSeries();

}
