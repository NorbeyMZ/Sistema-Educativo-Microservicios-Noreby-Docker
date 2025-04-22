package com.microservicio.estudiantes.controladores;

import com.microservicio.estudiantes.entidades.Estudiantes;
import com.microservicio.estudiantes.servicios.EstudianteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteServicios estudianteServicios;

    @GetMapping
    public ResponseEntity<List<Estudiantes>> listarTodos() {
        List<Estudiantes> estudiantes = estudianteServicios.findAll();
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiantes> obtenerPorId(@PathVariable Long id) {
        return estudianteServicios.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estudiantes> guardar(@RequestBody Estudiantes estudiante) {
        Estudiantes nuevoEstudiante = estudianteServicios.save(estudiante);
        return ResponseEntity.ok(nuevoEstudiante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estudianteServicios.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para obtener estudiantes por asignatura
    @GetMapping("/buscar-por-asignatura/{idAsignatura}")
    public ResponseEntity<List<Estudiantes>> obtenerEstudiantesPorAsignatura(@PathVariable Long idAsignatura) {
        List<Estudiantes> estudiantes = estudianteServicios.findAllByAsignaturaId(idAsignatura);
        if (estudiantes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estudiantes);
    }


}
