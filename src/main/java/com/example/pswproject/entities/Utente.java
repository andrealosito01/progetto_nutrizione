package com.example.pswproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId(){ return id; }

    @Basic(optional = false)
    @Column(length = 20, unique = true, nullable = false)
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

    @Transient
    private String password;

    public String getPassword(){ return password; }

    public void setPassword(String password) { this.password = password; }

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
    private Short altezza;

    public Short getAltezza() {
        return altezza;
    }

    public void setAltezza(Short altezza) {
        this.altezza = altezza;
    }

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name="piano_id")
    @JsonIgnore
    private Piano piano;

    public Piano getPiano() { return piano; }

    public void setPiano(Piano piano) { this.piano = piano; }


    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "utente_id")
    @OrderBy("data DESC")
    @JsonIgnore
    private List<Peso> pesi = new ArrayList<>();

    public List<Peso> getPesi() {
        return pesi;
    }

    public void setPesi(List<Peso> pesi) {
        this.pesi = pesi;
    }

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "utente_id")
    @JsonIgnore
    private List<Passi> passi = new ArrayList<>();

    public List<Passi> getPassi() { return passi; }

    public void setPassi(List<Passi> passi) { this.passi = passi; }

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "utente_id")
    @JsonIgnore
    private List<Misura> misure = new ArrayList<>();

    public List<Misura> getMisure() { return misure; }

    public void setMisure(List<Misura> misure) { this.misure = misure; }

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "utente_id")
    @JsonIgnore
    private List<Alimento> alimenti = new ArrayList<>();

    public List<Alimento> getAlimenti() { return alimenti; }

    public void setAlimenti(List<Alimento> alimenti) { this.alimenti = alimenti; }

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="utente_id")
    @JsonIgnore
    private List<Diario> diari = new ArrayList<>();

    public List<Diario> getDiari() { return diari; }

    public void setDiari(List<Diario> diari) { this.diari = diari; }

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "utente_id")
    @JsonIgnore
    private List<Scheda> schede = new ArrayList<>();

    public List<Scheda> getSchede() { return schede; }

    public void setSchede(List<Scheda> schede) { this.schede = schede; }

}