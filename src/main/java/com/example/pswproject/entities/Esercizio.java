package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "esercizi", uniqueConstraints = @UniqueConstraint(columnNames = {"Scheda", "Nome"}))
public class Esercizio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "scheda", nullable = false)
    private Scheda scheda;

    public Scheda getScheda() {
        return scheda;
    }

    public void setScheda(Scheda scheda) {
        this.scheda = scheda;
    }


    @Basic(optional = false)
    @Column(nullable = false, length = 32)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic(optional = false)
    @Column(nullable = false, length = 32)
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    private byte riposo;

    public byte getRiposo() {
        return riposo;
    }

    public void setRiposo(byte riposo) {
        this.riposo = riposo;
    }

    @OneToMany(mappedBy = "esercizio")
    @OrderBy("numero asc")
    private List<Serie> serie;

    public List<Serie> getSerie() {
        return serie;
    }

    public void setSerie(List<Serie> serie) {
        this.serie = serie;
    }
}
