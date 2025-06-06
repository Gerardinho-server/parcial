package com.sigcom.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario creador;

    @ManyToMany
    @JoinTable(
        name = "mision_recurso",
        joinColumns = @JoinColumn(name = "mision_id"),
        inverseJoinColumns = @JoinColumn(name = "recurso_id")
    )
    private List<Recurso> recursos;

    @ManyToMany
    @JoinTable(
        name = "mision_soldado",
        joinColumns = @JoinColumn(name = "mision_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> soldados = new ArrayList<>();

    // Getters y Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Usuario getCreador() { return creador; }
    public void setCreador(Usuario creador) { this.creador = creador; }

    public List<Recurso> getRecursos() { return recursos; }
    public void setRecursos(List<Recurso> recursos) { this.recursos = recursos; }

    public List<Usuario> getSoldados() { return soldados; }
    public void setSoldados(List<Usuario> soldados) { this.soldados = soldados; }
} 
