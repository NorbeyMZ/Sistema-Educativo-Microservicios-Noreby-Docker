package com.microservicio.usuario.servicio;

import com.microservicio.usuario.entidades.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {
    List<Usuario> obtenerTodosLosUsuarios();
    Optional<Usuario> obtenerUsuarioPorId(Long id);
    Usuario guardarUsuario(Usuario usuario); 
    Optional<Usuario> findByUsername(String username); 
}