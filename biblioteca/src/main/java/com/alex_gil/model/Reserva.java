package com.alex_gil.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Reserva implements Serializable {

    private int idReserva; // Identificador únic de la reserva
    private Date dataReserva; // Data de reserva
    private Date dataRetorn; // Data de retorn
    private Usuari usuari; // Relació Many-to-One amb Usuari
    private List<Llibre> llibres; // Relació One-to-Many amb Llibre

    // Constructor per defecte (necessari per Hibernate)
    public Reserva() {
    }

    // Constructor personalitzat
    public Reserva(Usuari usuari, Date dataReserva, Date dataRetorn) {
        this.dataReserva = dataReserva;
        this.dataRetorn = dataRetorn;
        this.usuari = usuari;
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

    public List<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(List<Llibre> llibres) {
        this.llibres = llibres;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", dataReserva=" + dataReserva +
                ", dataRetorn=" + dataRetorn +
                ", usuari=" + (usuari != null ? usuari.getDni() : "Sense usuari") +
                ", llibres=" + (llibres != null ? llibres.size() : 0) +
                '}';
    }
}
