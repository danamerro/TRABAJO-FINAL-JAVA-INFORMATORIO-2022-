package com.trabajofinalinfo.periodicoDM.repository;

import com.trabajofinalinfo.periodicoDM.model.entity.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Page<Autor> findByFullnameContaining(String fullname, Pageable pageable);
    Page<Autor> findByCreatedAtAfter(LocalDate date, Pageable pageable);
    @Query(
            value = "SELECT * FROM author WHERE author.fullname LIKE %:fullname% AND author.created_at > :date",
            countQuery = "SELECT count(*) FROM author",
            nativeQuery = true)
    Page<Autor> findByFullnameAndDateAfter(@Param("fullname") String fullname,
                                           @Param("date") LocalDate date, Pageable pageable);
}
