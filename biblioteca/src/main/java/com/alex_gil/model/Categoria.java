package com.alex_gil.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Categoria")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;

    @Column(nullable = false, unique = true)
    private String nomCategoria;

    @OneToOne(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Llibre llibre;

    public Categoria() {
    }

    public Categoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public Llibre getLlibre() {
        return llibre;
    }

    public void setLlibre(Llibre llibre) {
        this.llibre = llibre;
    }

    @Override
    public String toString() {
        return "Categoria{idCategoria=" + idCategoria + ", nomCategoria='" + nomCategoria + "'}";
    }
}