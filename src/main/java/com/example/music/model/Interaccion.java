package com.example.music.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "interacciones")
public class Interaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interaccionId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "cancion_id")
    private Long cancionId;

    @Column(name = "es_like")
    private boolean like;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    // Constructor vacío requerido por JPA
    public Interaccion() {}

    public Interaccion(Long usuarioId, Long cancionId, boolean like, Date fecha) {
        this.usuarioId = usuarioId;
        this.cancionId = cancionId;
        this.like = like;
        this.fecha = fecha;
    }

    public Long getInteraccionId() { return interaccionId; }
    public void setInteraccionId(Long interaccionId) { this.interaccionId = interaccionId; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getCancionId() { return cancionId; }
    public void setCancionId(Long cancionId) { this.cancionId = cancionId; }
    public boolean isLike() { return like; }
    public void setLike(boolean like) { this.like = like; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}