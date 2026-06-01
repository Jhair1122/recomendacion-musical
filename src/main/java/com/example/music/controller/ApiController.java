package com.example.music.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.music.data.DatosFicticios;
import com.example.music.model.Cancion;
import com.example.music.model.LoginRequest;
import com.example.music.model.Usuario;
import com.example.music.service.RecomendacionService;
import com.example.music.service.RecursividadService;
import com.example.music.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired private RecomendacionService recomendacionService;
    @Autowired private RecursividadService recursividadService;
    @Autowired private UsuarioService usuarioService;

    @PostMapping("/registro")
    public String registrar(@RequestBody LoginRequest req, HttpSession session) {
        Usuario u = usuarioService.registrarUsuario(req.getUsername(), req.getPassword());
        if (u == null) return "error: usuario ya existe";
        session.setAttribute("usuarioId", u.getId());
        return "ok";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req, HttpSession session) {
        Usuario u = usuarioService.autenticar(req.getUsername(), req.getPassword());
        if (u == null) return "error: credenciales inválidas";
        session.setAttribute("usuarioId", u.getId());
        return "ok";
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @GetMapping("/usuario-actual")
    public Usuario getUsuarioActual(HttpSession session) {
        Long id = (Long) session.getAttribute("usuarioId");
        if (id == null) return null;
        return usuarioService.getUsuarioById(id);
    }

    @GetMapping("/canciones/todas")
    public List<Cancion> getAllCanciones() {
        return DatosFicticios.getCanciones();
    }

    @GetMapping("/recomendar/similitud")
    public List<Cancion> recomendacionSimilitud(HttpSession session) {
        Long userId = (Long) session.getAttribute("usuarioId");
        if (userId == null) return List.of();
        return recomendacionService.recomendarPorSimilitud(userId);
    }

    @GetMapping("/recomendar/arbol")
    public List<Cancion> recomendacionArbol(HttpSession session) throws Exception {
        Long userId = (Long) session.getAttribute("usuarioId");
        if (userId == null) return List.of();
        return recomendacionService.recomendarPorArbol(userId);
    }

    @GetMapping("/playlist/recursiva/{cancionId}/{profundidad}")
    public List<Cancion> playlistRecursiva(@PathVariable Long cancionId,
                                           @PathVariable int profundidad,
                                           HttpSession session) {
        Long userId = (Long) session.getAttribute("usuarioId");
        if (userId == null) return List.of();
        return recursividadService.generarPlaylistRecursiva(userId, cancionId, profundidad);
    }

    @PostMapping("/like/{cancionId}")
    public void likeCancion(@PathVariable Long cancionId, HttpSession session) {
        Long userId = (Long) session.getAttribute("usuarioId");
        if (userId != null) {
            usuarioService.agregarInteraccion(userId, cancionId, true);
        }
    }
}