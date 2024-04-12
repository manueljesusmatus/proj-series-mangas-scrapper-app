package cl.dev.mjmh.serie.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestSerie {

    private Long id;
    private String tipo;
    private String nombre;
    private String imagen;
    private String url;

}
