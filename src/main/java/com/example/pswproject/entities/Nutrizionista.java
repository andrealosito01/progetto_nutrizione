package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="nutrizionisti")
public class Nutrizionista {

    @Id
    @Column(length = 20)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic(optional = false)
    @Column(nullable = false, unique = true, length = 319)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic(optional = false)
    @Column(nullable = false,length = 16)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic(optional = false)
    @Column(nullable = false,length = 16)
    private String cognome;

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataDiNascita;

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

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
