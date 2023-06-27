package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="riconoscimenti")
public class Riconoscimento {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long codice;

    public long getCodice() {
        return codice;
    }

    @Basic(optional = false)
    @Column(nullable = false, length = 64)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    @Column(length = 128)
    private String descrizione;

    public String getDescrizione(){
        return descrizione;
    }

    public void setDescrizione(String descrizione){
        this.descrizione = descrizione;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInizio;

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio){
        this.dataInizio = dataInizio;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dataScadenza;

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataSacdenza){
        this.dataScadenza = dataSacdenza;
    }

    @ManyToOne
    @JoinColumn(nullable = false, name="nutrizionista")
    private Nutrizionista nutrizionista;

    public Nutrizionista getNutrizionista() {
        return nutrizionista;
    }

    public void setNutrizionista(Nutrizionista nutrizionista){
        this.nutrizionista = nutrizionista;
    }
}
