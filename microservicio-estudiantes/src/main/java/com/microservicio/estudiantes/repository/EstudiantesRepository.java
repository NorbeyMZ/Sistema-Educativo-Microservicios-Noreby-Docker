package com.microservicio.estudiantes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservicio.estudiantes.entidades.Estudiantes;

import java.util.List;

@Repository
public interface EstudiantesRepository extends CrudRepository<Estudiantes, Long> {
    List<Estudiantes> findAllByAsignaturaId(Long asignaturaId);
}
