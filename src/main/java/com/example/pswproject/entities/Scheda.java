package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="schede")
public class Scheda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() { return id; }

    @Basic(optional = false)
    @Column(length = 32, nullable = false)
    private String nome;

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @Basic(optional = false)
    @Column(nullable = false)
    private boolean attiva = false;

    public boolean isAttiva() { return attiva; }

    public void setAttiva(boolean attiva) { this.attiva = attiva; }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "scheda_id")
    private List<Esercizio> esercizi = new ArrayList<>();

    public List<Esercizio> getEsercizi() { return esercizi; }

    public void setEsercizi(List<Esercizio> esercizi) { this.esercizi = esercizi; }

}
