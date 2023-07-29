package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="nutrizionisti")
@PrimaryKeyJoinColumn(name = "Username")
public class Nutrizionista extends Utente{

    @OneToMany(mappedBy = "nutrizionista")
    private Collection<Piano> pianiAssegnati;

    public Collection<Piano> getPianiAssegnati() {
        return pianiAssegnati;
    }

    public void setPianiAssegnati(Collection<Piano> pianiAssegnati) {
        this.pianiAssegnati = pianiAssegnati;
    }

    @OneToMany(mappedBy = "codice")
    private Collection<Riconoscimento> riconoscimenti;

    public Collection<Riconoscimento> getRiconoscimenti() {
        return riconoscimenti;
    }

    public void setRiconoscimenti(Collection<Riconoscimento> riconoscimenti) {
        this.riconoscimenti = riconoscimenti;
    }

    @OneToMany(mappedBy = "nutrizionista")
    private Collection<Scheda> schede;

    public Collection<Scheda> getSchede() {
        return schede;
    }

    public void setSchede(Collection<Scheda> schede) {
        this.schede = schede;
    }
}
