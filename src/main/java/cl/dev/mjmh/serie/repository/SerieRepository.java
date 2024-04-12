package cl.dev.mjmh.serie.repository;

import cl.dev.mjmh.serie.model.entity.SerieEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<SerieEntity, Long> {
}
