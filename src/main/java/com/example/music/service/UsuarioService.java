package com.example.music.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.music.data.DatosFicticios;
import com.example.music.model.Cancion;
import com.example.music.model.Interaccion;
import com.example.music.model.Usuario;
import com.example.music.repository.InteraccionRepository;
import com.example.music.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private InteraccionRepository interaccionRepository;

    public Usuario registrarUsuario(String username, String password) {
        if (usuarioRepository.findByUsername(username).isPresent()) return null;
        
        Usuario u = new Usuario(null, username, password, username, 18);
        u = usuarioRepository.save(u);

        // Likes iniciales con canciones populares
        final Long userId = u.getId();
        List<Cancion> populares = DatosFicticios.getCanciones().stream()
            .sorted((a, b) -> Integer.compare(b.getPopularidad(), a.getPopularidad()))
            .limit(10)
            .collect(Collectors.toList());
        for (Cancion c : populares) {
            interaccionRepository.save(
                new Interaccion(userId, c.getId(), true, new Date())
            );
        }
        return u;
    }

    public Usuario autenticar(String username, String password) {
        return usuarioRepository.findByUsername(username)
            .filter(u -> u.getPassword().equals(password))
            .orElse(null);
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void agregarInteraccion(Long userId, Long cancionId, boolean like) {
        interaccionRepository.save(
            new Interaccion(userId, cancionId, like, new Date())
        );
    }
}