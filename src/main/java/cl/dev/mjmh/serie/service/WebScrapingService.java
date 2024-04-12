package cl.dev.mjmh.serie.service;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

import java.util.List;

public interface WebScrapingService {

    String getPage(String xpath);

    String getPageFromString(String htmlString);

    List<HtmlElement> getContent(String xpath);
}
