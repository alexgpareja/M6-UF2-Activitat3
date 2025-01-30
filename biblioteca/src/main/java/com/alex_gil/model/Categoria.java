package com.alex_gil.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Categoria")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria; // Identificador únic de la categoria

    @Column(nullable = false, unique = true)
    private String nomCategoria; // Nom de la categoria (únic i obligatori)

    @OneToOne(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Llibre llibre; // Relació 1 a 1 amb Llibre (un llibre té una categoria)

    // Constructor
    public Categoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    // Getters i Setters
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
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nomCategoria='" + nomCategoria + '\'' +
                '}';
    }
}