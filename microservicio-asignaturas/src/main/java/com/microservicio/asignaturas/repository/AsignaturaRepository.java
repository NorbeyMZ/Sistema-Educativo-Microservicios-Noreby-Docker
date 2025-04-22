package com.microservicio.asignaturas.repository;

import com.microservicio.asignaturas.entidades.Asignatura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepository extends CrudRepository<Asignatura, Long> {
}
