package cl.dev.mjmh.serie.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 632218490902384621L;

    private Date date;
    private String message;
    private String description;

}
