package com.trabajofinalinfo.periodicoDM.repository;

import com.trabajofinalinfo.periodicoDM.model.entity.Fuente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuenteRepository extends JpaRepository<Fuente, Long> {
    /**
     * JPaRepository entiende el tipo de busqueda que yo hago siempre que sea sencilla
     * El Page me permite dividir la busqueda en paginas (paginar busqueda)
     */
    Page<Fuente> findByNameContaining(String name, Pageable pageable);
}
