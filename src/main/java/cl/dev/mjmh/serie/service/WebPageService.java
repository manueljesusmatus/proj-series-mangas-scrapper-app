package cl.dev.mjmh.serie.service;

import cl.dev.mjmh.serie.model.entity.WebPageEntity;
import cl.dev.mjmh.serie.model.entity.dto.WebPageDto;

import org.springframework.data.domain.Page;

import java.util.List;

public interface WebPageService {

    WebPageDto convertToDto(WebPageEntity webPage);
    WebPageEntity convertToEntity(WebPageDto webPageDto);
    WebPageDto create(String pageContent);
    WebPageDto readById(long id);
    List<WebPageDto> readAll();
    Page<WebPageDto> readByPage(int pageNumber, int pageSize);
    String readLast();
    List<WebPageDto> findAtDay(String fecha);
    List<WebPageDto> findAtDay(String fecha, String dateFormat);
    List<WebPageDto> findBetweenDays(String fechaInicio, String fechaFin);
    List<WebPageDto> findBetweenDays(String fechaInicio, String fechaFin, String dateFormat);


}
