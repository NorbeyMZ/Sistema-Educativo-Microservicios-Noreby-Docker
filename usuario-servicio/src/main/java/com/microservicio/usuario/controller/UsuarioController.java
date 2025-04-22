package com.microservicio.usuario.controller;

import com.microservicio.usuario.entidades.Usuario;
import com.microservicio.usuario.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PreAuthorize("hasRole('ESTUDIANTE')")
    @GetMapping("/estudiante")
    public String estudianteEndpoint() {
        return "Hola ESTUDIANTE, accediste correctamente ";
    }

    @PreAuthorize("hasRole('PROFESOR')")
    @GetMapping("/profesor")
    public String profesorEndpoint() {
        return "Hola PROFESOR, accediste correctamente ";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/solo-admins")
    public String soloAdmins() {
        return "Bienvenido, administrador";
    }

    
    @PostMapping("/crear")
    public String crearUsuario(@RequestBody Usuario usuario) {
        usuarioServicio.guardarUsuario(usuario);
        return "Usuario creado con éxito ";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/todos")   
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioServicio.obtenerTodosLosUsuarios();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        Optional<Usuario> usuarioOpt = usuarioServicio.obtenerUsuarioPorId(id);

        if (usuarioOpt.isPresent()) {
            Usuario usuarioExistente = usuarioOpt.get();

            usuarioExistente.setUsername(usuarioActualizado.getUsername());

            // Codificar la nueva contraseña si es diferente
            if (!usuarioActualizado.getPassword().equals(usuarioExistente.getPassword())) {
                String passwordCodificada = passwordEncoder.encode(usuarioActualizado.getPassword());
                usuarioExistente.setPassword(passwordCodificada);
            }

            usuarioExistente.setRol(usuarioActualizado.getRol());

            usuarioServicio.guardarUsuario(usuarioExistente);
            return "Usuario actualizado con éxito";
        } else {
            return "Usuario no encontrado";
        }
    }

}
