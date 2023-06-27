package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="clienti")
public class Cliente {

    @Id
    @Column(length = 20)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    @Basic(optional = false)
    @Column(length = 319, unique = true, nullable = false)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Basic(optional = false)
    @Column(length = 16, nullable = false)
    private String nome;

    public String getNome(){
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic(optional = false)
    @Column(length = 16, nullable = false)
    private String cognome;

    public String getCognome(){
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

    @Basic
    private Byte altezza;

    public Byte getAltezza() {
        return altezza;
    }

    public void setAltezza(Byte altezza) {
        this.altezza = altezza;
    }

    @OneToOne(mappedBy = "cliente")
    private Piano piano;

    public Piano getPiano() {
        return piano;
    }

    public void setPiano(Piano piano) {
        this.piano = piano;
    }

    @OneToMany(mappedBy = "cliente")
    @OrderBy("data asc")
    private List<Peso> pesi;

    public List<Peso> getPesi() {
        return pesi;
    }

    public void setPesi(List<Peso> pesi) {
        this.pesi = pesi;
    }

    @OneToMany(mappedBy = "cliente")
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

    @OneToOne(mappedBy = "cliente")
    private Scheda scheda;

    public Scheda getScheda() {
        return scheda;
    }

    public void setScheda(Scheda scheda) {
        this.scheda = scheda;
    }
}
