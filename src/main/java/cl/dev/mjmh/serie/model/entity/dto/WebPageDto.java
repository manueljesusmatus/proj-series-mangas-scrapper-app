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
public class WebPageDto {

    private Date createdAt;
    private Long size;

}
