package cl.dev.mjmh.serie.repository;

import cl.dev.mjmh.serie.model.entity.WebPageEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WebPageRepository extends JpaRepository<WebPageEntity, Long> {

    @Query(value = "from WebPageEntity wp where createdAt BETWEEN :startDate AND :endDate")
    List<WebPageEntity> findAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    List<WebPageEntity> findByCreatedAt(Date date);
    WebPageEntity findFirstByOrderByCreatedAtDesc();

}
