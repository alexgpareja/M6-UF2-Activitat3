package com.alex_gil.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Reserva implements Serializable {

    private int idReserva; // Identificador únic de la reserva
    private Date dataReserva; // Data de reserva
    private Date dataRetorn; // Data de retorn
    private Usuari usuari; // Relació Many-to-One amb Usuari
    private Set<Llibre> llibres; // Relació Many-to-Many amb Llibre

    // Constructors, getters, setters, i toString
    public Reserva() {
    }

    public Reserva(Date dataReserva, Date dataRetorn, Usuari usuari, Set<Llibre> llibres) {
        this.dataReserva = dataReserva;
        this.dataRetorn = dataRetorn;
        this.usuari = usuari;
        this.llibres = llibres;
    }

    // Getters i Setters
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Date getDataRetorn() {
        return dataRetorn;
    }

    public void setDataRetorn(Date dataRetorn) {
        this.dataRetorn = dataRetorn;
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", dataReserva=" + dataReserva +
                ", dataRetorn=" + dataRetorn +
                ", usuari=" + usuari.getDni() +
                ", llibres=" + llibres.size() +
                '}';
    }

}
