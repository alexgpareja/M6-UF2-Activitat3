package com.alex_gil.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Usuari")
public class Usuari implements Serializable {

    @Id
    private String dni;

    @Column(nullable = false)
    private String nomUsuari;

    @Column(nullable = false)
    private String telefon;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "usuari")
    private Set<Reserva> reserves;

    public Usuari() {
    }

    public Usuari(String dni, String nomUsuari, String telefon, String email) {
        this.dni = dni;
        this.nomUsuari = nomUsuari;
        this.telefon = telefon;
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Reserva> getReserves() {
        return reserves;
    }

    public void setReserves(Set<Reserva> reserves) {
        this.reserves = reserves;
    }
}
