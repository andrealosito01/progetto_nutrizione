package com.example.pswproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "contatti")
public class Contatto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @Basic(optional = false)
    @Column(length = 16, nullable = false)
    private String tipo;

    public String getTipo(){return tipo;}

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    @Basic(optional = false)
    @Column(length = 319, nullable = false)
    private String nome;

    public String getNome(){return nome;}

    public void setNome(String nome){
        this.nome = nome;
    }

    @Basic(optional = false)
    @Column(length = 512, nullable = false)
    private String link;

    public String getLink(){return link;}

    public void setLink(String link){
        this.link = link;
    }

    @ManyToOne
    @JoinColumn(nullable = false, name="Nutrizionista")
    private Nutrizionista nutrizionista;

    public Nutrizionista getNutrizionista() {
        return nutrizionista;
    }

    public void setNutrizionista(Nutrizionista nutrizionista) {
        this.nutrizionista = nutrizionista;
    }

}
