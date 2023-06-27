package com.example.pswproject.entities;

import javax.persistence.*;

@Entity
@Table(name="voci")
public class Voce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name="diario", nullable = false)
    private Diario diario;

    public Diario getDiario() {
        return diario;
    }

    public void setDiario(Diario diario) {
        this.diario = diario;
    }

    @Basic
    private short quantita;

    public short getQuantita() {
        return quantita;
    }

    public void setQuantita(short quantita) {
        this.quantita = quantita;
    }

    @Basic(optional = false)
    @Column(nullable = false, length = 13)
    private String prodotto;

    public String getProdotto() {
        return prodotto;
    }

    public void setProdotto(String prodotto) {
        this.prodotto = prodotto;
    }

    @Basic(optional = false)
    @Column(nullable = false, length = 9)
    private String pasto;

    public String getPasto() {
        return pasto;
    }

    public void setPasto(String pasto){
        this.pasto = pasto;
    }

}
