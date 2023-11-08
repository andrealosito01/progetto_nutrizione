package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "esercizi")
public class Esercizio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() { return id; }

    @Basic(optional = false)
    @Column(length = 32, nullable = false)
    private String nome;

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @Basic
    @Column(length = 256)
    private String descrizione;

    public String getDescrizione() { return descrizione; }

    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "esercizio_id")
    private List<Serie> serie = new ArrayList<>();

    public List<Serie> getSerie() { return serie; }

    public void setSerie(List<Serie> serie){ this.serie = serie; }

}
