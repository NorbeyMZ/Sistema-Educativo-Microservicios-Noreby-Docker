package com.microservicio.asignaturas.servicios;

import com.microservicio.asignaturas.cliente.EstudianteFeignClient;
import com.microservicio.asignaturas.entidades.Asignatura;
import com.microservicio.asignaturas.http.response.EstudianteByAsignaturaResponse;
import com.microservicio.asignaturas.dto.EstudianteDTO;
import com.microservicio.asignaturas.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AsignaturaServiciosImpl implements AsignaturaServicios {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private EstudianteFeignClient estudianteFeignClient;

    @Override
    public List<Asignatura> findAll() {
        return StreamSupport
                .stream(asignaturaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Asignatura> findById(Long id) {
        return asignaturaRepository.findById(id);
    }

    @Override
    public Asignatura save(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    @Override
    public EstudianteByAsignaturaResponse findEstudiantesByIdAsignatura(Long idAsignatura) {
        Optional<Asignatura> asignaturaOptional = asignaturaRepository.findById(idAsignatura);
        if (asignaturaOptional.isEmpty()) {
            throw new RuntimeException("Asignatura no encontrada con id: " + idAsignatura);
        }

        Asignatura asignatura = asignaturaOptional.get();
        List<EstudianteDTO> estudiantes = estudianteFeignClient.obtenerEstudiantesPorAsignatura(idAsignatura);

        return EstudianteByAsignaturaResponse.builder()
                .nombreAsignatura(asignatura.getNombre())
                .profesor(asignatura.getProfesor())
                .estudianteDTOList(estudiantes)
                .build();
    }
}
