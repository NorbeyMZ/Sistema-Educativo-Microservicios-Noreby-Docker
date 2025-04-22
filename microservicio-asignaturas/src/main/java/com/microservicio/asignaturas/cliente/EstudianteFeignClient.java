package com.microservicio.asignaturas.cliente;

import com.microservicio.asignaturas.dto.EstudianteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-estudiantes")
public interface EstudianteFeignClient {

    @GetMapping("/api/estudiantes/buscar-por-asignatura/{idAsignatura}")
    List<EstudianteDTO> obtenerEstudiantesPorAsignatura(@PathVariable Long idAsignatura);
}
