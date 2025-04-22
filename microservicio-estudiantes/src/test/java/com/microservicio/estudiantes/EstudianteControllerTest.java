package com.microservicio.estudiantes;
import com.microservicio.estudiantes.controladores.EstudianteController;
import com.microservicio.estudiantes.entidades.Estudiantes;
import com.microservicio.estudiantes.servicios.EstudianteServicios;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EstudianteController.class)
public class EstudianteControllerTest {

    @MockBean
    private EstudianteServicios estudianteServicios;

    @Autowired
    private MockMvc mockMvc;  // Asegúrate de que MockMvc sea inyectado correctamente

    @Test
    public void testGuardarEstudiante() throws Exception {
        Estudiantes estudiante = new Estudiantes();
        estudiante.setId(1L);
        estudiante.setNombre("Laura");
        estudiante.setEmail("laura@correo.com");

        Mockito.when(estudianteServicios.save(Mockito.any(Estudiantes.class))).thenReturn(estudiante);

        mockMvc.perform(post("/api/estudiantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"Laura\",\"email\":\"laura@correo.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Laura"))
                .andExpect(jsonPath("$.email").value("laura@correo.com"));
    }

    @Test
    public void testObtenerEstudiantePorId() throws Exception {
        Estudiantes estudiante = new Estudiantes();
        estudiante.setId(1L);
        estudiante.setNombre("Laura");
        estudiante.setEmail("laura@correo.com");

        Mockito.when(estudianteServicios.findById(1L)).thenReturn(java.util.Optional.of(estudiante));

        mockMvc.perform(get("/api/estudiantes/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Laura"))
                .andExpect(jsonPath("$.email").value("laura@correo.com"));
    }

    @Test
    public void testObtenerEstudiantePorId_NotFound() throws Exception {
        Mockito.when(estudianteServicios.findById(1L)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/api/estudiantes/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testEliminarEstudiante() throws Exception {
        mockMvc.perform(delete("/api/estudiantes/{id}", 1L))
                .andExpect(status().isNoContent());

        Mockito.verify(estudianteServicios, Mockito.times(1)).deleteById(1L);
    }
}
