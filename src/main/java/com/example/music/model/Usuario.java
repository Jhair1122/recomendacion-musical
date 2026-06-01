package com.example.music.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    private String nombre;
    private int edad;

    @Transient
    private List<Interaccion> historial = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Usuario() {}

    public Usuario(Long id, String username, String password, String nombre, int edad) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.edad = edad;
        this.historial = new ArrayList<>();
    }

    public Usuario(Long id, String username, String password, String nombre, int edad, List<Interaccion> historial) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.edad = edad;
        this.historial = historial != null ? historial : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public List<Interaccion> getHistorial() { return historial; }
    public void setHistorial(List<Interaccion> historial) { this.historial = historial; }
}