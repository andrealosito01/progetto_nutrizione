package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="misure")
public class Misura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() { return id; }

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short braccioDx;

    public Short getBraccioDx() { return braccioDx; }

    public void setBraccioDx(Short braccioDx) { this.braccioDx = braccioDx; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short braccioSx;

    public Short getBraccioSx() { return braccioSx; }

    public void setBraccioSx(Short braccioSx) { this.braccioSx = braccioSx; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short torace;

    public Short getTorace() { return torace; }

    public void setTorace(Short torace) { this.torace = torace; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short vita;

    public Short getVita() { return vita; }

    public void setVita(Short vita) { this.vita = vita; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short fianchi;

    public Short getFianchi() { return fianchi; }

    public void setFianchi(Short fianchi) { this.fianchi = fianchi; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short gambaDx;

    public Short getGambaDx() { return gambaDx; }

    public void setGambaDx(Short gambaDx) { this.gambaDx = gambaDx; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short gambaSx;

    public Short getGambaSx() { return gambaSx; }

    public void setGambaSx(Short gambaSx) { this.gambaSx = gambaSx; }

    @ManyToOne
    @JoinColumn(name="utente_id")
    private Utente utente;

    public Utente getUtente() { return utente; }

    public void setUtente(Utente utente) { this.utente = utente; }

}
