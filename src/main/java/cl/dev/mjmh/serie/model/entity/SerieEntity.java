package cl.dev.mjmh.serie.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Table(name = "serie")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SerieEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2272624102992773665L;

    @Id
    private Long id;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "url")
    private String url;
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

}
