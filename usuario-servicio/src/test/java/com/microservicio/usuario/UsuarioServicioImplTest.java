package com.microservicio.usuario;
import com.microservicio.usuario.servicio.UsuarioServicioImpl;
import com.microservicio.usuario.entidades.Usuario;
import com.microservicio.usuario.repositorio.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsuarioServicioImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServicioImpl usuarioServicio;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByUsername() {
        // Arrange
        String username = "marta";
        Usuario mockUsuario = new Usuario();
        mockUsuario.setUsername(username);
        mockUsuario.setRol("PROFESOR");

        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.of(mockUsuario));

        // Act
        Optional<Usuario> resultado = usuarioServicio.findByUsername(username);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("marta", resultado.get().getUsername());
        assertEquals("PROFESOR", resultado.get().getRol());

        verify(usuarioRepository, times(1)).findByUsername(username);
    }
}