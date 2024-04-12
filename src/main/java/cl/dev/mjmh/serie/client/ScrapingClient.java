package cl.dev.mjmh.serie.client;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import jakarta.annotation.PreDestroy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScrapingClient {

    private static final Logger LOGGER = LogManager.getLogger();

    private final WebClient webClient;

    public ScrapingClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public HtmlPage getPage(String url) throws IOException {
        return webClient.getPage(url);
    }

    public HtmlPage loadPageFromString(String htmlString) throws IOException {
        return webClient.loadHtmlCodeIntoCurrentWindow(htmlString);
    }

    @PreDestroy
    public void destroy() {
        LOGGER.info("{} - Shutdown ...", webClient.getClass().getName());
        webClient.close();
    }

}
