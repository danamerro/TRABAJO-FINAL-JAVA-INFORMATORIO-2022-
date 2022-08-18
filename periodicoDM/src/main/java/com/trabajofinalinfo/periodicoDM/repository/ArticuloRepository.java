package com.trabajofinalinfo.periodicoDM.repository;

import com.trabajofinalinfo.periodicoDM.model.entity.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    /**
     * Si la busqueda es mas rebuscada para el Jpa debo usar @Query, este me permite introducir una busqueda
     * de SQL
     */
    @Query(
            value = "SELECT * " +
                    "FROM articulo ar " +
                    "INNER JOIN autor au ON ar.author_id = au.id " +
                    "WHERE ar.title LIKE %:filter% " +
                    "OR ar.description LIKE %:filter% " +
                    "OR ar.content LIKE %:filter% " +
                    "OR au.fullname LIKE %:filter% " +
                    "AND ar.published = true",
            countQuery = "SELECT count(*) FROM articulo",
            nativeQuery = true)
    Page<Articulo> findByFilter(@Param("filter") String filter, Pageable pageable);
}
