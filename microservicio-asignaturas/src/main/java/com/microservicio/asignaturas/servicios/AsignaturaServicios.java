package com.microservicio.asignaturas.servicios;

import com.microservicio.asignaturas.entidades.Asignatura;
import com.microservicio.asignaturas.http.response.EstudianteByAsignaturaResponse;

import java.util.List;
import java.util.Optional;

public interface AsignaturaServicios {
    List<Asignatura> findAll();
    Optional<Asignatura> findById(Long id);
    Asignatura save(Asignatura asignatura);
    EstudianteByAsignaturaResponse findEstudiantesByIdAsignatura(Long idAsignatura);
}
