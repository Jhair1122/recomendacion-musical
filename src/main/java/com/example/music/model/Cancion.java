package com.example.music.model;

public class Cancion {
    private Long id;
    private String titulo;
    private String artista;
    private String genero;
    private int popularidad;
    private double energia;
    private double bailabilidad;
    private String urlPreview;   // Ruta al archivo MP3 local (ej: "/audios/cancion.mp3")
    private String portadaUrl;

    // Constructor completo
    public Cancion(Long id, String titulo, String artista, String genero, 
                   int popularidad, double energia, double bailabilidad, 
                   String urlPreview, String portadaUrl) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.popularidad = popularidad;
        this.energia = energia;
        this.bailabilidad = bailabilidad;
        this.urlPreview = urlPreview;
        this.portadaUrl = portadaUrl;
    }

    // Getters y setters (igual que antes, pero sin youtubeVideoId)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public int getPopularidad() { return popularidad; }
    public void setPopularidad(int popularidad) { this.popularidad = popularidad; }
    public double getEnergia() { return energia; }
    public void setEnergia(double energia) { this.energia = energia; }
    public double getBailabilidad() { return bailabilidad; }
    public void setBailabilidad(double bailabilidad) { this.bailabilidad = bailabilidad; }
    public String getUrlPreview() { return urlPreview; }
    public void setUrlPreview(String urlPreview) { this.urlPreview = urlPreview; }
    public String getPortadaUrl() { return portadaUrl; }
    public void setPortadaUrl(String portadaUrl) { this.portadaUrl = portadaUrl; }
}