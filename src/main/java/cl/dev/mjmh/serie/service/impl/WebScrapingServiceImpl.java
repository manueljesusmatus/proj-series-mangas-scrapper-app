package cl.dev.mjmh.serie.service.impl;

import cl.dev.mjmh.serie.client.ScrapingClient;
import cl.dev.mjmh.serie.exception.InternalServerException;
import cl.dev.mjmh.serie.service.WebScrapingService;

import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class WebScrapingServiceImpl implements WebScrapingService {

    @Value("${webpage.url}")
    private String url;

    private static final Logger LOGGER = LogManager.getLogger();

    private final ScrapingClient scrapingClient;

    public WebScrapingServiceImpl(ScrapingClient scrapingClient) {
        this.scrapingClient = scrapingClient;
    }

    @Override
    public String getPage(String xpath) {
        try {
            LOGGER.info("recuperando html desde url: {}, xpath: {}", url, xpath);
            HtmlPage page = scrapingClient.getPage(url);
            HtmlDivision div = page.getFirstByXPath(xpath);
            return div.asXml();
        } catch (IOException | RuntimeException e) {
            throw new InternalServerException(e);
        }
    }

    @Override
    public String getPageFromString(String htmlString) {
        try {
            LOGGER.info("recuperando html desde string");
            HtmlPage page = scrapingClient.loadPageFromString(htmlString);
            return page.asXml();
        } catch (IOException | RuntimeException e) {
            throw new InternalServerException(e);
        }
    }

    @Override
    public List<HtmlElement> getContent(String xpath) {
        try {
            LOGGER.info("recuperando elementos desde url: {}, xpath: {}", url, xpath);
            HtmlPage page = scrapingClient.getPage(url);
            return page.getByXPath(xpath);
        } catch (IOException | RuntimeException e) {
            throw new InternalServerException(e);
        }
    }

}
