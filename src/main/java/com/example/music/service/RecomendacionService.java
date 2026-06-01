package com.example.music.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.music.data.DatosFicticios;
import com.example.music.model.Cancion;
import com.example.music.model.Interaccion;
import com.example.music.repository.InteraccionRepository;

import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

@Service
public class RecomendacionService {

    @Autowired private InteraccionRepository interaccionRepository;

    public List<Cancion> recomendarPorSimilitud(Long usuarioId) {
        List<Interaccion> misInter = interaccionRepository.findByUsuarioId(usuarioId);
        Set<Long> gustadas = misInter.stream()
            .filter(Interaccion::isLike).map(Interaccion::getCancionId)
            .collect(Collectors.toSet());

        // Obtener todos los demás usuarios desde la BD
        Map<Long, Set<Long>> gustosPorUsuario = new HashMap<>();
        for (Interaccion i : interaccionRepository.findAll()) {
            if (!i.getUsuarioId().equals(usuarioId) && i.isLike()) {
                gustosPorUsuario.computeIfAbsent(i.getUsuarioId(), k -> new HashSet<>())
                    .add(i.getCancionId());
            }
        }

        Map<Long, Integer> similitud = new HashMap<>();
        for (var entry : gustosPorUsuario.entrySet()) {
            long comunes = gustadas.stream().filter(entry.getValue()::contains).count();
            if (comunes > 0) similitud.put(entry.getKey(), (int) comunes);
        }

        List<Long> vecinos = similitud.entrySet().stream()
            .sorted((a, b) -> b.getValue() - a.getValue())
            .limit(2).map(Map.Entry::getKey).collect(Collectors.toList());

        Set<Long> recomendadas = new HashSet<>();
        for (Long vecino : vecinos) {
            gustosPorUsuario.getOrDefault(vecino, new HashSet<>()).stream()
                .filter(id -> !gustadas.contains(id))
                .forEach(recomendadas::add);
        }

        return DatosFicticios.getCanciones().stream()
            .filter(c -> recomendadas.contains(c.getId()))
            .limit(5).collect(Collectors.toList());
    }

    public List<Cancion> recomendarPorArbol(Long usuarioId) throws Exception {
        List<Interaccion> todasInter = interaccionRepository.findAll();
        if (todasInter.isEmpty()) return new ArrayList<>();

        ArrayList<Attribute> atts = new ArrayList<>();
        atts.add(new Attribute("energia"));
        atts.add(new Attribute("bailabilidad"));
        atts.add(new Attribute("popularidad"));
        List<String> generos = Arrays.asList("Pop","Alternativo","Electrónica","Hip Hop","Rock","Anime","Latino","K-Pop","Trap","Balada");
        atts.add(new Attribute("genero", generos));
        atts.add(new Attribute("like", Arrays.asList("0", "1")));

        Instances data = new Instances("Recomendacion", atts, 0);
        data.setClassIndex(data.numAttributes() - 1);

        for (Interaccion inter : todasInter) {
            DatosFicticios.getCanciones().stream()
                .filter(c -> c.getId().equals(inter.getCancionId())).findFirst()
                .ifPresent(c -> {
                    Instance inst = new DenseInstance(5);
                    inst.setValue(atts.get(0), c.getEnergia());
                    inst.setValue(atts.get(1), c.getBailabilidad());
                    inst.setValue(atts.get(2), c.getPopularidad());
                    inst.setValue(atts.get(3), c.getGenero());
                    inst.setValue(atts.get(4), inter.isLike() ? "1" : "0");
                    data.add(inst);
                });
        }

        if (data.numInstances() < 5) return new ArrayList<>();

        J48 arbol = new J48();
        arbol.buildClassifier(data);

        Set<Long> escuchadas = todasInter.stream()
            .filter(i -> i.getUsuarioId().equals(usuarioId))
            .map(Interaccion::getCancionId).collect(Collectors.toSet());

        List<Cancion> recomendadas = new ArrayList<>();
        for (Cancion c : DatosFicticios.getCanciones()) {
            if (escuchadas.contains(c.getId())) continue;
            Instance inst = new DenseInstance(5);
            inst.setValue(atts.get(0), c.getEnergia());
            inst.setValue(atts.get(1), c.getBailabilidad());
            inst.setValue(atts.get(2), c.getPopularidad());
            inst.setValue(atts.get(3), c.getGenero());
            inst.setDataset(data);
            if (arbol.classifyInstance(inst) == 1.0) recomendadas.add(c);
            if (recomendadas.size() >= 5) break;
        }
        return recomendadas;
    }
}