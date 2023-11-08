package com.example.pswproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "serie")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() { return id; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short numero;

    public Short getNumero() { return numero; }

    public void setNumero(Short numero) { this.numero = numero;}

    @Basic(optional = false)
    @Column(nullable = false)
    private Short ripetizioni;

    public Short getRipetizioni() { return ripetizioni; }

    public void setRipetizioni(Short ripetizioni) { this.ripetizioni = ripetizioni;}

    @Basic(optional = false)
    @Column(nullable = false)
    private Short carico;

    public Short getCarico() { return carico; }

    public void setCarico(Short carico) { this.carico = carico;}

    @Basic
    private Short durata;

    public Short getDurata() { return durata; }

    public void setDurata(Short durata) { this.durata = durata;}

}
