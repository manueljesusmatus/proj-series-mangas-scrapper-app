package cl.dev.mjmh.serie.configuration;

import cl.dev.mjmh.serie.model.ConfigurationSearch;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

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
    @Value("${webpage.debug.onFailingStatusCode}")
    private boolean debugOnFailingStatusCode;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public WebClient webClient() {
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(debugOnFailingStatusCode);
        return webClient;
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
