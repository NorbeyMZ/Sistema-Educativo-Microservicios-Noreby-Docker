package com.microservicio.estudiantes.servicios;

import java.util.List;
import java.util.Optional;

import com.microservicio.estudiantes.entidades.Estudiantes;

public interface EstudianteServicios {
    List<Estudiantes> findAll();
    Optional<Estudiantes> findById(Long id);
    Estudiantes save(Estudiantes estudiante);
    void deleteById(Long id);
    List<Estudiantes> findAllByAsignaturaId(Long asignaturaId);
}
