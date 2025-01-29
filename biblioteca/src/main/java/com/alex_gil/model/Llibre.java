package com.alex_gil.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Llibres")
public class Llibre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLlibre;

    @Column(unique = true, nullable = false)
    private long isbn;

    @Column(nullable = false)
    private String titol;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private int anyPublicacio;

    @Column(nullable = false)
    private boolean disponibilitat;

    @OneToOne // 1 llibre només pot tenir 1 categoria
    @JoinColumn(name = "id_categoria", nullable = false, unique = true)
    private Categoria categoria;

    @ManyToMany(mappedBy = "llibres")
    private Set<Reserva> reserves;

    public Llibre() {
    }

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
