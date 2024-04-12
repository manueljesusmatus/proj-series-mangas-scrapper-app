package cl.dev.mjmh.serie.service.impl;

import cl.dev.mjmh.serie.model.entity.SerieEntity;
import cl.dev.mjmh.serie.model.entity.dto.RequestSerie;
import cl.dev.mjmh.serie.model.entity.dto.SerieDto;
import cl.dev.mjmh.serie.exception.EntityNotFoundException;
import cl.dev.mjmh.serie.repository.SerieRepository;
import cl.dev.mjmh.serie.service.SerieService;

import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SerieServiceImpl implements SerieService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final SerieRepository serieRepository;
    private final ModelMapper modelMapper;

    public SerieServiceImpl(SerieRepository serieRepository, ModelMapper modelMapper) {
        this.serieRepository = serieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SerieDto convertToDto(SerieEntity serie) {
        return modelMapper.map(serie, SerieDto.class);
    }

    @Override
    public SerieEntity convertToEntity(SerieDto serie) {
        return modelMapper.map(serie, SerieEntity.class);
    }

    @Override
    public SerieEntity convertToEntity(RequestSerie serie) {
        return modelMapper.map(serie, SerieEntity.class);
    }

    @Override
    public SerieDto createSerie(RequestSerie serie) {
        try {
            SerieEntity serieToCreate = convertToEntity(serie);
            serieToCreate.setCreatedAt(new Date());
            serieToCreate.setUpdatedAt(new Date());
            return createSerie(serieToCreate);
        } catch (Exception ex) {
            LOGGER.error("error creando serie", ex);
            return null;
        }

    }

    @Override
    public SerieDto createSerie(SerieEntity serie) {
        LOGGER.info("creando serie [{}]: {}", serie.getId(), serie.getNombre());
        try {
            SerieEntity serieSaved = serieRepository.save(serie);
            return convertToDto(serieSaved);
        } catch (Exception ex) {
            LOGGER.error("error creando serie", ex);
            return null;
        }

    }

    @Override
    public List<SerieDto> createAll(@NonNull List<SerieEntity> series) {
        LOGGER.info("creando {} series", series.size());
        try {
            return series.stream().map(this::createSerie).filter(Objects::nonNull).toList();
        } catch (Exception ex) {
            LOGGER.error("error creando series", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public SerieDto readSerieById(Long id) {
        LOGGER.info("recuperando serie con id: {}", id);
        Optional<SerieEntity> serie = serieRepository.findById(id);
        if (serie.isPresent())
            return convertToDto(serie.get());
        throw new EntityNotFoundException(SerieEntity.class);
    }

    @Override
    public List<SerieDto> readAllSeries() {
        LOGGER.info("recuperando todas las series");
        List<SerieEntity> series = serieRepository.findAll();
        return series.stream().map(this::convertToDto).toList();
    }

    @Override
    public Page<SerieDto> readByPage(int pageNumber, int pageSize) {
        LOGGER.info("recuperando pagina {} con {} registros de serie", pageNumber, pageSize);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<SerieEntity> page = serieRepository.findAll(pageable);
        return page.map(this::convertToDto);
    }

    @Override
    public SerieDto updateSerie(Long id) {
        LOGGER.info("actualizando ultima revision");
        Optional<SerieEntity> serieOpt = serieRepository.findById(id);
        if (serieOpt.isPresent()) {
            SerieEntity serie = serieOpt.get();
            serie.setUpdatedAt(new Date());
            return convertToDto(serieRepository.save(serie));
        }
        throw new EntityNotFoundException(SerieEntity.class);
    }

}
