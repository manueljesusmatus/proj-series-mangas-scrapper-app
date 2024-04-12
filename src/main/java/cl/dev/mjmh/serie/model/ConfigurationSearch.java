package cl.dev.mjmh.serie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigurationSearch implements Serializable {

    @Serial
    private static final long serialVersionUID = -7958074082470642877L;

    private String xpath;
    private String xpathTitle;
    private String xpathTipo;
    private String xpathImagen;
    private String idAttr;
    private String urlAttr;

}
