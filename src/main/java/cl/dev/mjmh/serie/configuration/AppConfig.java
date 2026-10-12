package cl.dev.mjmh.serie.configuration;

import cl.dev.mjmh.serie.model.ConfigurationSearch;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${webpage.xpath.main}")
    private String xpath;
    @Value("${webpage.xpath.title}")
    private String xpathTitle;
    @Value("${webpage.xpath.tipo}")
    private String xpathTipo;
    @Value("${webpage.xpath.imagen}")
    private String xpathImagen;
    @Value("${webpage.attribute.id}")
    private String idAttr;
    @Value("${webpage.attribute.url}")
    private String urlAttr;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ConfigurationSearch configurationSearch() {
        return ConfigurationSearch.builder()
                .xpath(xpath)
                .xpathTitle(xpathTitle)
                .xpathTipo(xpathTipo)
                .xpathImagen(xpathImagen)
                .idAttr(idAttr)
                .urlAttr(urlAttr)
                .build();
    }
}
