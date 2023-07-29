package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name="pazienti")
@PrimaryKeyJoinColumn(name = "Username")
public class Paziente extends Utente{

    @Basic
    private Short altezza;

    public Short getAltezza() {
        return altezza;
    }

    public void setAltezza(Short altezza) {
        this.altezza = altezza;
    }

    @OneToOne(mappedBy = "paziente")
    private Piano piano;

    public Piano getPiano() {
        return piano;
    }

    public void setPiano(Piano piano) {
        this.piano = piano;
    }

    /*@OneToMany(mappedBy = "paziente")
    @OrderBy("data asc")
    private List<Peso> pesi;

    public List<Peso> getPesi() {
        return pesi;
    }

    public void setPesi(List<Peso> pesi) {
        this.pesi = pesi;
    }*/

    @OneToMany(mappedBy = "paziente")
    @MapKey(name = "giorno")
    private Map<Date,Diario> diari;

    public Map<Date, Diario> getDiari() {
        return diari;
    }

    public void setDiari(Map<Date, Diario> diari) {
        this.diari = diari;
    }

    /*private Collection<Diario> diari;

    public Collection<Diario> getDiari() {
        return diari;
    }

    public void setDiari(Collection<Diario> diari) {
        this.diari = diari;
    }*/

    @OneToOne(mappedBy = "paziente")
    private Scheda scheda;

    public Scheda getScheda() {
        return scheda;
    }

    public void setScheda(Scheda scheda) {
        this.scheda = scheda;
    }
}
