package cl.dev.mjmh.serie.service.impl;

import jakarta.validation.constraints.NotBlank;

import cl.dev.mjmh.serie.model.entity.WebPageEntity;
import cl.dev.mjmh.serie.model.entity.dto.WebPageDto;
import cl.dev.mjmh.serie.exception.EntityNotFoundException;
import cl.dev.mjmh.serie.exception.InternalServerException;
import cl.dev.mjmh.serie.repository.WebPageRepository;
import cl.dev.mjmh.serie.service.WebPageService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WebPageServiceImpl implements WebPageService {

    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String ERROR_FORMAT = "Error al formatear la fecha %s con formato %s";
    private static final String ERROR_FORMAT_ARG = "Error al formatear con formato de fecha invalido";

    private static final Logger LOGGER = LogManager.getLogger();

    private final WebPageRepository webPageRepository;
    private final ModelMapper modelMapper;

    public WebPageServiceImpl(
            WebPageRepository webPageRepository,
            ModelMapper modelMapper) {
        this.webPageRepository = webPageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WebPageDto convertToDto(WebPageEntity webPage) {
        WebPageDto webPageDto = modelMapper.map(webPage, WebPageDto.class);
        webPageDto.setSize((long) webPage.getContent().length());
        return webPageDto;
    }

    @Override
    public WebPageEntity convertToEntity(WebPageDto webPageDto) {
        return modelMapper.map(webPageDto, WebPageEntity.class);
    }

    @Override
    public WebPageDto create(String pageContent) {
        WebPageEntity webPage = WebPageEntity.builder()
                .content(pageContent)
                .createdAt(new Date())
                .build();
        return convertToDto(webPageRepository.save(webPage));
    }

    @Override
    public WebPageDto readById(long id) {
        Optional<WebPageEntity> optional = webPageRepository.findById(id);
        if (optional.isPresent()) {
            return convertToDto(optional.get());
        }
        throw new EntityNotFoundException(WebPageEntity.class);
    }

    @Override
    public List<WebPageDto> readAll() {
        return webPageRepository.findAll().stream().map(this::convertToDto).toList();
    }

    @Override
    public Page<WebPageDto> readByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return webPageRepository.findAll(pageable).map(this::convertToDto);
    }

    @Override
    public String readLast() {
        return webPageRepository.findFirstByOrderByCreatedAtDesc().getContent();
    }

    @Override
    public List<WebPageDto> findAtDay(@NotBlank String fecha) {
        return findAtDay(fecha, DATE_FORMAT);
    }

    @Override
    public List<WebPageDto> findAtDay(@NotBlank String fecha, @NotBlank String dateFormat) {
        try {
            LOGGER.info("Buscando paginas con fecha[{}]: {}", dateFormat, fecha);
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            Date date = formatter.parse(fecha);
            List<WebPageEntity> results = webPageRepository.findByCreatedAt(date);
            return results.stream()
                    .map(this::convertToDto)
                    .toList();
        } catch (ParseException e) {
            throw new InternalServerException(ERROR_FORMAT, fecha, DATE_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new InternalServerException(ERROR_FORMAT_ARG);
        } catch (RuntimeException e) {
            throw new InternalServerException(e);
        }
    }

    @Override
    public List<WebPageDto> findBetweenDays(@NotBlank String fechaInicio, @NotBlank String fechaFin) {
        return findBetweenDays(fechaInicio, fechaFin, DATE_FORMAT);
    }

    @Override
    public List<WebPageDto> findBetweenDays(@NotBlank String fechaInicio, @NotBlank String fechaFin, @NotBlank String dateFormat) {
        try {
            LOGGER.info("Buscando paginas entre fechas [{}]: {} - {} ", dateFormat, fechaInicio, fechaFin);
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            Date dateInit = formatter.parse(fechaInicio);
            Date dateEnd = formatter.parse(fechaFin);
            List<WebPageEntity> results = webPageRepository.findAllBetweenDates(dateInit, dateEnd);
            return results.stream()
                    .map(this::convertToDto)
                    .toList();
        } catch (ParseException e) {
            throw new InternalServerException(ERROR_FORMAT, String.format("%s - %s", fechaInicio, fechaFin), DATE_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new InternalServerException(ERROR_FORMAT_ARG);
        } catch (RuntimeException e) {
            throw new InternalServerException(e);
        }
    }


}
