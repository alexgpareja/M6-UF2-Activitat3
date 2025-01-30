package com.alex_gil.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Llibres")
public class Llibre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLlibre; // Identificador únic del llibre

    @Column(unique = true, nullable = false)
    private long isbn; // ISBN del llibre (únic i obligatori)

    @Column(nullable = false)
    private String titol; // Títol del llibre (obligatori)

    @Column(nullable = false)
    private String autor; // Autor del llibre (obligatori)

    @Column(nullable = false)
    private int anyPublicacio; // Any de publicació del llibre (obligatori)

    @Column(nullable = false)
    private boolean disponibilitat; // Disponibilitat del llibre (true si està disponible, false si no)

    @OneToOne // Relació 1 a 1 amb Categoria (un llibre té una categoria)
    @JoinColumn(name = "id_categoria", nullable = false, unique = true)
    private Categoria categoria;

    @ManyToMany(mappedBy = "llibres") // Relació N a N amb Reserva (un llibre pot estar en diverses reserves)
    private Set<Reserva> reserves;

    // Constructor
    public Llibre(long isbn, String titol, String autor, int anyPublicacio, boolean disponibilitat,
            Categoria categoria) {
        this.isbn = isbn;
        this.titol = titol;
        this.autor = autor;
        this.anyPublicacio = anyPublicacio;
        this.disponibilitat = disponibilitat;
        this.categoria = categoria;
    }

    // Getters i Setters
    public int getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(int idLlibre) {
        this.idLlibre = idLlibre;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnyPublicacio() {
        return anyPublicacio;
    }

    public void setAnyPublicacio(int anyPublicacio) {
        this.anyPublicacio = anyPublicacio;
    }

    public boolean isDisponibilitat() {
        return disponibilitat;
    }

    public void setDisponibilitat(boolean disponibilitat) {
        this.disponibilitat = disponibilitat;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Reserva> getReserves() {
        return reserves;
    }

    public void setReserves(Set<Reserva> reserves) {
        this.reserves = reserves;
    }

    @Override
    public String toString() {
        return "Llibre{" +
                "idLlibre=" + idLlibre +
                ", isbn=" + isbn +
                ", titol='" + titol + '\'' +
                ", autor='" + autor + '\'' +
                ", anyPublicacio=" + anyPublicacio +
                ", disponibilitat=" + disponibilitat +
                ", categoria=" + categoria.getNomCategoria() +
                '}';
    }
}