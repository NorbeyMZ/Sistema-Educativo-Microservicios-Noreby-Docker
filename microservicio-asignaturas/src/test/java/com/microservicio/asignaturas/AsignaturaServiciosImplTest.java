package com.microservicio.asignaturas;

import com.microservicio.asignaturas.servicios.AsignaturaServiciosImpl;
import com.microservicio.asignaturas.entidades.Asignatura;
import com.microservicio.asignaturas.repository.AsignaturaRepository;
import com.microservicio.asignaturas.dto.EstudianteDTO;
import com.microservicio.asignaturas.http.response.EstudianteByAsignaturaResponse;
import com.microservicio.asignaturas.cliente.EstudianteFeignClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AsignaturaServiciosImplTest {

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @Mock
    private EstudianteFeignClient estudianteFeignClient;

    @InjectMocks
    private AsignaturaServiciosImpl asignaturaServicios;

    @Test
    void testGuardarAsignatura() {
        Asignatura asignatura = new Asignatura();
        asignatura.setNombre("Matemáticas");
        asignatura.setProfesor("Profe Andrea");

        when(asignaturaRepository.save(any(Asignatura.class))).thenReturn(asignatura);

        Asignatura resultado = asignaturaServicios.save(asignatura);

        assertNotNull(resultado);
        assertEquals("Matemáticas", resultado.getNombre());
        assertEquals("Profe Andrea", resultado.getProfesor());
        verify(asignaturaRepository, times(1)).save(asignatura);
    }

    @Test
    void testBuscarAsignaturaPorId() {
        Asignatura asignatura = new Asignatura(1L, "Física", "Profe Juan");

        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(asignatura));

        Optional<Asignatura> resultado = asignaturaServicios.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Física", resultado.get().getNombre());
        assertEquals("Profe Juan", resultado.get().getProfesor());
        verify(asignaturaRepository).findById(1L);
    }

    @Test
    void testListarTodasLasAsignaturas() {
        List<Asignatura> lista = List.of(
            new Asignatura(1L, "Biología", "Profe Ana"),
            new Asignatura(2L, "Química", "Profe Carlos")
        );

        when(asignaturaRepository.findAll()).thenReturn(lista);

        List<Asignatura> resultado = asignaturaServicios.findAll();

        assertEquals(2, resultado.size());
        assertEquals("Biología", resultado.get(0).getNombre());
        verify(asignaturaRepository).findAll();
    }

    @Test
    void testFindEstudiantesByIdAsignatura() {
        Long idAsignatura = 1L;
        Asignatura asignatura = new Asignatura(idAsignatura, "Historia", "Profe Julián");
    
        // Asegúrate de que los objetos sean del tipo correcto
        EstudianteDTO estudiante1 = new EstudianteDTO(1L, "Laura", "laura@mail.com");
        EstudianteDTO estudiante2 = new EstudianteDTO(2L, "Mateo", "mateo@mail.com");
        
        // Usamos List.of para crear la lista de estudiantes
        List<EstudianteDTO> estudiantes = List.of(estudiante1, estudiante2);
    
        // Simulamos la respuesta del repositorio y del cliente Feign
        when(asignaturaRepository.findById(idAsignatura)).thenReturn(Optional.of(asignatura));
        when(estudianteFeignClient.obtenerEstudiantesPorAsignatura(idAsignatura)).thenReturn(estudiantes);
    
       
        EstudianteByAsignaturaResponse response = asignaturaServicios.findEstudiantesByIdAsignatura(idAsignatura);
    
        
        assertEquals("Historia", response.getNombreAsignatura());
        assertEquals("Profe Julián", response.getProfesor());
        assertEquals(2, response.getEstudianteDTOList().size());
    
        verify(asignaturaRepository).findById(idAsignatura);
        verify(estudianteFeignClient).obtenerEstudiantesPorAsignatura(idAsignatura);
    }
    

}
