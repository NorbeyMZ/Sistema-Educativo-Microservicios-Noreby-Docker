package com.microservicio.usuario.security;

import com.microservicio.usuario.entidades.Usuario;
import com.microservicio.usuario.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        System.out.println("Petición recibida en /auth/login");
        // Buscar al usuario en la base de datos por username
        Optional<Usuario> usuarioOpt = usuarioServicio.findByUsername(request.getUsername());

        // Verificar si el usuario existe
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // Imprimir para depuración
            System.out.println("Usuario encontrado: " + usuario.getUsername());
            System.out.println("Password en BD (hash): " + usuario.getPassword());
            System.out.println("Password ingresado: " + request.getPassword());

            // Comparar la contraseña ingresada con la almacenada (con hash)
            boolean passwordValido = passwordEncoder.matches(request.getPassword(), usuario.getPassword());
            System.out.println("¿Password válido?: " + passwordValido);

            // Si las contraseñas coinciden, generar el token
            if (passwordValido) {
                String role = usuario.getRol(); // Obtener el rol del usuario desde la base de datos
                return JwtUtil.generateToken(usuario.getUsername(), role); // Generar y devolver el token JWT
            }
        } else {
            // Si el usuario no se encuentra
            System.out.println("Usuario no encontrado");
        }

        // Si las credenciales son inválidas
        return "Credenciales inválidas";
    }
}
