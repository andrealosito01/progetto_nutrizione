package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="nutrizionisti")
@PrimaryKeyJoinColumn(name = "Username")
public class Nutrizionista extends Utente{

    @Basic
    @Column(length = 64)
    private String foto;

    public String getFoto(){return foto;}

    public void setFoto(String foto){
        this.foto = foto;
    }

    @Basic
    @Column(length = 512)
    private String presentazione;

    public String getPresentazione(){return presentazione;}

    public void setPresentazione(String presentazione){
        this.presentazione = presentazione;
    }

    @Basic
    @Column(length = 1500)
    private String esperienzaFormazione;

    public String getEsperienzaFormazione(){return esperienzaFormazione;}

    public void setEsperienzaFormazione(String esperienzaFormazione){
        this.esperienzaFormazione = esperienzaFormazione;
    }

    @Basic
    @Column(length = 256)
    private String specializzazione;

    public String getSpecializzazione(){return specializzazione;}

    public void setSpecializzazione(String specializzazione){
        this.specializzazione = specializzazione;
    }

    @Basic
    @Column(length = 512)
    private String filosofia;

    public String getFilosofia(){return filosofia;}

    public void setFilosofia(String filosofia){
        this.filosofia = filosofia;
    }

    @Basic
    @Column(length = 512)
    private String servizi;

    public String getServizi(){return servizi;}

    public void setServizi(String servizi){
        this.servizi = servizi;
    }

    @OneToMany(mappedBy = "nutrizionista")
    private Collection<Contatto> contatti;

    public Collection<Contatto> getContatti() {
        return contatti;
    }

    public void setContatti(Collection<Contatto> contatti) {
        this.contatti = contatti;
    }

    @OneToMany(mappedBy = "nutrizionista")
    private Collection<Piano> pianiAssegnati;

    public Collection<Piano> getPianiAssegnati() {
        return pianiAssegnati;
    }

    public void setPianiAssegnati(Collection<Piano> pianiAssegnati) {
        this.pianiAssegnati = pianiAssegnati;
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
