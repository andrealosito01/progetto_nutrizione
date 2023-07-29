package com.example.pswproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="utenti")
@Inheritance(strategy = InheritanceType.JOINED)
public class Utente {

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

}
