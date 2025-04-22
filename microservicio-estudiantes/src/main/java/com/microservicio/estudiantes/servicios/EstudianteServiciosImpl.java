package com.microservicio.estudiantes.servicios;

import com.microservicio.estudiantes.repository.EstudiantesRepository;
import com.microservicio.estudiantes.entidades.Estudiantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiciosImpl implements EstudianteServicios {

    @Autowired
    private EstudiantesRepository repository;

    @Override
    public List<Estudiantes> findAll() {
        return (List<Estudiantes>) repository.findAll();
    }

    @Override
    public Optional<Estudiantes> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Estudiantes save(Estudiantes estudiante) {
        return repository.save(estudiante);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Estudiantes> findAllByAsignaturaId(Long asignaturaId) {
        return repository.findAllByAsignaturaId(asignaturaId);
    }
}
