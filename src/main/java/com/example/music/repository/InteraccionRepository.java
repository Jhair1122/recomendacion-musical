package com.example.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.music.model.Interaccion;

public interface InteraccionRepository extends JpaRepository<Interaccion, Long> {
    List<Interaccion> findByUsuarioId(Long usuarioId);
}