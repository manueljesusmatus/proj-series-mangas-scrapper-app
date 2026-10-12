package cl.dev.mjmh.serie.client;

import cl.dev.mjmh.serie.exception.LoadResourceException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScrapingClient {

    @Value("${webpage.debug.onFailingStatusCode}")
    private boolean debugOnFailingStatusCode;

    public HtmlPage getPage(String url) throws IOException {
        try (final WebClient webClient = new WebClient()) {
            setOptions(webClient);
            return webClient.getPage(url);
        } catch (RuntimeException | IOException e) {
            throw new LoadResourceException("Error al obtener page de URL: " + url, e);
        }
    }

    public HtmlPage loadPageFromString(String htmlString) throws IOException {
        try (final WebClient webClient = new WebClient()) {
            setOptions(webClient);
            return webClient.loadHtmlCodeIntoCurrentWindow(htmlString);
        } catch (RuntimeException | IOException e) {
            throw new LoadResourceException("Error al cargar page de string: ", e);
        }
    }

    private void setOptions(WebClient webClient) {
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(debugOnFailingStatusCode);
    }

}
