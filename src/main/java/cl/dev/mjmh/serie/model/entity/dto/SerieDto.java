package cl.dev.mjmh.serie.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SerieDto {

    private String tipo;
    private String nombre;
    private String imagen;
    private String url;
    private Date createdAt;
    private Date updatedAt;

}
