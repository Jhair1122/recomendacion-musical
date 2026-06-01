package com.example.music.service;

import com.example.music.model.Cancion;
import com.example.music.data.DatosFicticios;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RecursividadService {

    public List<Cancion> generarPlaylistRecursiva(Long usuarioId, Long cancionActualId, int profundidad) {
        Set<Long> yaRecomendadas = new HashSet<>();
        return generarRecursivo(usuarioId, cancionActualId, profundidad, yaRecomendadas);
    }

    private List<Cancion> generarRecursivo(Long usuarioId, Long cancionActualId, int profundidad, Set<Long> yaRecomendadas) {
        if (profundidad == 0) return new ArrayList<>();
        Cancion actual = DatosFicticios.getCanciones().stream()
                .filter(c -> c.getId().equals(cancionActualId)).findFirst().orElse(null);
        if (actual == null) return new ArrayList<>();

        Cancion siguiente = DatosFicticios.getCanciones().stream()
                .filter(c -> !c.getId().equals(cancionActualId) && !yaRecomendadas.contains(c.getId()))
                .filter(c -> c.getGenero().equals(actual.getGenero()) && Math.abs(c.getEnergia() - actual.getEnergia()) < 0.3)
                .findFirst()
                .orElse(DatosFicticios.getCanciones().stream()
                        .filter(c -> !c.getId().equals(cancionActualId) && !yaRecomendadas.contains(c.getId()))
                        .findFirst().orElse(null));

        if (siguiente == null) return new ArrayList<>();
        yaRecomendadas.add(siguiente.getId());
        List<Cancion> lista = new ArrayList<>();
        lista.add(siguiente);
        lista.addAll(generarRecursivo(usuarioId, siguiente.getId(), profundidad - 1, yaRecomendadas));
        return lista;
    }
}