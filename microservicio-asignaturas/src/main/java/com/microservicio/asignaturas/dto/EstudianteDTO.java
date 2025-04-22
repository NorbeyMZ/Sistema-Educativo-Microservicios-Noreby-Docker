package com.microservicio.asignaturas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteDTO {
    private String nombre;
    private String apellido;
    private String email;
    private Long asignaturaId;

    public EstudianteDTO(Long asignaturaId, String nombre, String apellido) {
        this.asignaturaId = asignaturaId;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
