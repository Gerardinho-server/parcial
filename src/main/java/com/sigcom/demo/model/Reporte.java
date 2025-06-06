package com.sigcom.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String contenido;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "mision_id")
    private Mision mision;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    public Reporte() {
        this.fecha = LocalDate.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Mision getMision() { return mision; }
    public void setMision(Mision mision) { this.mision = mision; }

    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }
}
