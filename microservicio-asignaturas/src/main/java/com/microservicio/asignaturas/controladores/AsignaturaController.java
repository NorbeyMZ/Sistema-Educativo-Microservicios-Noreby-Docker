package com.microservicio.asignaturas.controladores;

import com.microservicio.asignaturas.entidades.Asignatura;
import com.microservicio.asignaturas.http.response.EstudianteByAsignaturaResponse;
import com.microservicio.asignaturas.servicios.AsignaturaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaServicios asignaturaServicios;

    @GetMapping
    public ResponseEntity<List<Asignatura>> listarAsignatura() {
        return ResponseEntity.ok(asignaturaServicios.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> detalle(@PathVariable Long id) {
        Optional<Asignatura> asignaturaOptional = asignaturaServicios.findById(id);
        return asignaturaOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Asignatura> crear(@RequestBody Asignatura asignatura) {
        return ResponseEntity.ok(asignaturaServicios.save(asignatura));
    }

    @GetMapping("/{idAsignatura}/estudiantes")
    public ResponseEntity<EstudianteByAsignaturaResponse> obtenerEstudiantesPorAsignatura(@PathVariable Long idAsignatura) {
        EstudianteByAsignaturaResponse respuesta = asignaturaServicios.findEstudiantesByIdAsignatura(idAsignatura);
        return ResponseEntity.ok(respuesta);
    }
}
