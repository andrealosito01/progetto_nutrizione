package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "schede")
public class Scheda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @OneToOne
    @JoinColumn(name="paziente")
    private Paziente paziente;

    public Paziente getPaziente() {
        return paziente;
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    @ManyToOne
    @JoinColumn(name="nutrizionista")
    private Nutrizionista nutrizionista;

    public Nutrizionista getNutrizionista() {
        return nutrizionista;
    }

    public void setNutrizionista(Nutrizionista nutrizionista) {
        this.nutrizionista = nutrizionista;
    }

    @Basic(optional = false)
    @Column(nullable = false, length = 32)
    private String obiettivo;

    public String getObiettivo() {
        return obiettivo;
    }

    public void setObiettivo(String obiettivo) {
        this.obiettivo = obiettivo;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date DataInizio;

    public Date getDataInizio() {
        return DataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        DataInizio = dataInizio;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    private Date DataFine;

    public Date getDataFine() {
        return DataFine;
    }

    public void setDataFine(Date dataFine) {
        DataFine = dataFine;
    }

    @Basic
    @Column(length = 128)
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @OneToMany(mappedBy = "scheda")
    private Collection<Esercizio> esercizi;

    public Collection<Esercizio> getEsercizi() {
        return esercizi;
    }

    public void setEsercizi(Collection<Esercizio> esercizi) {
        this.esercizi = esercizi;
    }
}
