package cl.dev.mjmh.serie.service.impl;

import cl.dev.mjmh.serie.model.ConfigurationSearch;
import cl.dev.mjmh.serie.model.entity.SerieEntity;
import cl.dev.mjmh.serie.model.entity.dto.SerieDto;
import cl.dev.mjmh.serie.exception.LoadResourceException;
import cl.dev.mjmh.serie.service.DownloadService;
import cl.dev.mjmh.serie.service.SerieService;
import cl.dev.mjmh.serie.service.WebScrapingService;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static cl.dev.mjmh.serie.util.WebScrapingUtil.extractURL;
import static cl.dev.mjmh.serie.util.WebScrapingUtil.getAttribute;
import static cl.dev.mjmh.serie.util.WebScrapingUtil.getElement;

@Service
public class DownloadServiceImpl implements DownloadService {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int MAX_NOMBRE_LENGTH = 150;

    private final ConfigurationSearch config;
    private final WebScrapingService webScrapingService;
    private final SerieService serieService;

    public DownloadServiceImpl(
            ConfigurationSearch configurationSearch,
            WebScrapingService webScrapingService,
            SerieService serieService
    ) {
        this.config = configurationSearch;
        this.webScrapingService = webScrapingService;
        this.serieService = serieService;
    }

    @Override
    public List<SerieDto> downloadSeries() {
        try {
            LOGGER.info("descargando series ...");
            List<HtmlElement> elements = webScrapingService.getContent(config.getXpath());
            if(Objects.isNull(elements) || elements.isEmpty()) {
                throw new LoadResourceException("No se han encontrado series para descargar");
            }
            elements.remove(0);

            List<SerieEntity> serieEntities = elements.stream().map(e -> {
                try {
                    return toSerie(e);
                } catch (Exception ex) {
                    LOGGER.error("Error en la descargar la serie", ex);
                    return null;
                }
            }).filter(Objects::nonNull).toList();

            return CollectionUtils.isEmpty(serieEntities) ?
                     Collections.emptyList() : serieService.createAll(serieEntities);
        } catch (Exception e) {
            throw new LoadResourceException(e);
        }
    }

    private SerieEntity toSerie(HtmlElement element) {
        DomElement domE = element.getFirstElementChild();
        String nombre = getElement(domE, config.getXpathTitle()).getTextContent();
        return SerieEntity.builder()
                .id(Long.parseLong(getAttribute(element, config.getIdAttr()).getValue()))
                .nombre(nombre.substring(0, Math.min(nombre.length(), MAX_NOMBRE_LENGTH)))
                .tipo(getElement(domE, config.getXpathTipo()).getTextContent())
                .imagen(extractURL(getElement(domE, config.getXpathImagen()).getTextContent()))
                .url(getAttribute(domE, config.getUrlAttr()).getValue())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

}
