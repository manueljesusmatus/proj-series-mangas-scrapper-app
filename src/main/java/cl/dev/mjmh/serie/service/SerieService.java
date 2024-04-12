package cl.dev.mjmh.serie.service;

import cl.dev.mjmh.serie.exception.EntityCreateException;
import cl.dev.mjmh.serie.model.entity.SerieEntity;
import cl.dev.mjmh.serie.model.entity.dto.RequestSerie;
import cl.dev.mjmh.serie.model.entity.dto.SerieDto;

import org.springframework.data.domain.Page;

import java.util.List;

public interface SerieService {
    SerieDto convertToDto(SerieEntity serie);

    SerieEntity convertToEntity(SerieDto serie);

    SerieEntity convertToEntity(RequestSerie serie);

    SerieDto createSerie(RequestSerie serie);

    SerieDto createSerie(SerieEntity serie) throws EntityCreateException;

    List<SerieDto> createAll(List<SerieEntity> series);

    SerieDto readSerieById(Long id);

    List<SerieDto> readAllSeries();

    Page<SerieDto> readByPage(int pageNumber, int pageSize);

    SerieDto updateSerie(Long id);
}
