package com.example.pswproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="pesi")
public class Peso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    private Double valore;

    public Double getValore() {
        return valore;
    }

    public void setValore(Double valore) {
        this.valore = valore;
    }

    @ManyToOne
    @JoinColumn(name="utente_id")
    @JsonIgnore
    private Utente utente;

    public Utente getUtente() { return utente; }

    public void setUtente(Utente utente) { this.utente = utente; }

    public String toString(){
        return "Peso[" + this.data +","+this.valore+"]";
    }
}
