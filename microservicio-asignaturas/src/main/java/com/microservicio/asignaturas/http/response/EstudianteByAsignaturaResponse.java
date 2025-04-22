package com.microservicio.asignaturas.http.response;

import com.microservicio.asignaturas.dto.EstudianteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstudianteByAsignaturaResponse {
    private String nombreAsignatura;
    private String profesor;
    private List<EstudianteDTO> estudianteDTOList;
}
