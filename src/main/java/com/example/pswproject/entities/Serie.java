package com.example.pswproject.entities;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "serie", uniqueConstraints = @UniqueConstraint(columnNames = {"Esercizio","Numero"}))
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "esercizio", nullable = false)
    private Esercizio esercizio;

    public Esercizio getEsercizio() {
        return esercizio;
    }

    public void setEsercizio(Esercizio esercizio) {
        this.esercizio = esercizio;
    }

    @Basic
    private byte numero;

    public byte getNumero() {
        return numero;
    }

    public void setNumero(byte numero) {
        this.numero = numero;
    }

    @Basic
    private Byte ripetizioni;

    public Byte getRipetizioni() {
        return ripetizioni;
    }

    public void setRipetizioni(Byte ripetizioni) {
        this.ripetizioni = ripetizioni;
    }

    @Basic
    @ColumnDefault("0")
    private byte carico;

    public byte getCarico() {
        return carico;
    }

    public void setCarico(byte carico) {
        this.carico = carico;
    }

    @Basic
    private Byte durata;

    public Byte getDurata() {
        return durata;
    }

    public void setDurata(Byte durata) {
        this.durata = durata;
    }
}
