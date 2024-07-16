package com.Desafio.ForoHub.Tema;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {

    Page<Tema> findByStatusTrue(Pageable paginacion);
}
