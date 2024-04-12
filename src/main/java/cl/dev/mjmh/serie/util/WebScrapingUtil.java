package cl.dev.mjmh.serie.util;

import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.DomElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScrapingUtil {

    private WebScrapingUtil() {
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static DomElement getElement(DomElement domElement, String xPathRelativo) {
        String xPathSearch = domElement.getCanonicalXPath() + xPathRelativo;
        DomElement result = domElement.getFirstByXPath(xPathSearch);
        LOGGER.debug("DomElement {}", result);
        return result;
    }

    public static DomElement getElement(DomElement domElement) {
        return getElement(domElement, "");
    }

    public static DomAttr getAttribute(DomElement domElement, String attribute) {
        DomAttr attr = domElement.getAttributeNode(attribute);
        LOGGER.debug("DomAttr {}", attr);
        return attr;
    }

    public static DomAttr getAttribute(DomElement domElement, String xPathRelativo, String attribute) {
        DomElement element = getElement(domElement, xPathRelativo);
        return getAttribute(element, attribute);
    }

    public static String extractURL(String input) {
        // Expresión regular para extraer la URL
        String regex = "url\\('([^']+)'\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Si se encuentra la coincidencia, extrae la URL
        if (matcher.find()) {
            return matcher.group(1); // El grupo 1 contiene la URL
        } else {
            return null; // Si no se encuentra ninguna coincidencia, devuelve null
        }
    }
}
