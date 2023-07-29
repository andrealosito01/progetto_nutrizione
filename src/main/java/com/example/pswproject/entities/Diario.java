package com.example.pswproject.entities;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="diari", uniqueConstraints = @UniqueConstraint(columnNames = {"paziente","giorno"}))
public class Diario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "paziente",nullable = false)
    private Paziente paziente;

    public Paziente getPaziente() {
        return paziente;
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date giorno;

    public Date getGiorno() {
        return giorno;
    }

    public void setGiorno(Date giorno) {
        this.giorno = giorno;
    }

    @Basic(optional = false)
    @Column(nullable = false, scale = 3, precision = 2)
    @ColumnDefault("0")
    private BigDecimal acqua;

    public BigDecimal getAcqua() {
        return acqua;
    }

    public void setAcqua(BigDecimal acqua) {
        this.acqua = acqua;
    }

    @OneToMany(mappedBy = "diario")
    Collection<Voce> voci;

    public Collection<Voce> getVoci() {
        return voci;
    }

    public void setVoci(Collection<Voce> voci) {
        this.voci = voci;
    }
}
