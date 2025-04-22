package com.microservicio.estudiantes;

import com.microservicio.estudiantes.servicios.EstudianteServiciosImpl;
import com.microservicio.estudiantes.entidades.Estudiantes;
import com.microservicio.estudiantes.repository.EstudiantesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EstudianteServiciosImplTest {

    @Mock
    private EstudiantesRepository repository;

    @InjectMocks
    private EstudianteServiciosImpl estudianteServicios;

    @Test
    public void testGuardarEstudiante() {
        // Estudiante de prueba
        Estudiantes estudiante = new Estudiantes();
        estudiante.setId(1L);
        estudiante.setNombre("Laura");
        estudiante.setEmail("laura@correo.com");

        //Simular comportamiento del repositorio
        Mockito.when(repository.save(estudiante)).thenReturn(estudiante);

        //Llamar al método
        Estudiantes resultado = estudianteServicios.save(estudiante);

        //Verificar resultado
        assertNotNull(resultado);
        assertEquals("Laura", resultado.getNombre());
        assertEquals("laura@correo.com", resultado.getEmail());
    }
}