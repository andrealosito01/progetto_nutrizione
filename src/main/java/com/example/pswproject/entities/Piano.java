package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="piani")
public class Piano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @OneToOne
    @JoinColumn(name="paziente")
    private Paziente paziente;

    public Paziente getPaziente() {
        return paziente;
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
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

    @Basic
    private short energia;

    public short getEnergia() {
        return energia;
    }

    public void setEnergia(short energia) {
        this.energia = energia;
    }

    @Basic
    private short proteine;

    public short getProteine() {
        return proteine;
    }

    public void setProteine(short proteine) {
        this.proteine = proteine;
    }

    @Basic
    private short carboidrati;

    public short getCarboidrati() {
        return carboidrati;
    }

    public void setCarboidrati(short carboidrati) {
        this.carboidrati = carboidrati;
    }

    @Basic
    private short fibre;

    public short getFibre() {
        return fibre;
    }

    public void setFibre(short fibre) {
        this.fibre = fibre;
    }

    @Basic
    private short zucchero;

    public short getZucchero() {
        return zucchero;
    }

    public void setZucchero(short zucchero) {
        this.zucchero = zucchero;
    }

    @Basic
    private short grassi;

    public short getGrassi() {
        return grassi;
    }

    public void setGrassi(short grassi) {
        this.grassi = grassi;
    }

    @Basic
    private short grassiSaturi;

    public short getGrassiSaturi() {
        return grassiSaturi;
    }

    public void setGrassiSaturi(short grassiSaturi) {
        this.grassiSaturi = grassiSaturi;
    }

    @Basic
    private Short grassiPolinsaturi;

    public Short getGrassiPolinsaturi() {
        return grassiPolinsaturi;
    }

    public void setGrassiPolinsaturi(Short grassiPolinsaturi) {
        this.grassiPolinsaturi = grassiPolinsaturi;
    }

    @Basic
    private Short grassiMonoinsaturi;

    public Short getGrassiMonoinsaturi() {
        return grassiMonoinsaturi;
    }

    public void setGrassiMonoinsaturi(Short grassiMonoinsaturi) {
        this.grassiMonoinsaturi = grassiMonoinsaturi;
    }

    @Basic
    private Short grassiTrans;

    public Short getGrassiTrans() {
        return grassiTrans;
    }

    public void setGrassiTrans(Short grassiTrans) {
        this.grassiTrans = grassiTrans;
    }

    @Basic
    private Short colesterolo;

    public Short getColesterolo() {
        return colesterolo;
    }

    public void setColesterolo(Short colesterolo) {
        this.colesterolo = colesterolo;
    }

    @Basic
    private Short sodio;

    public Short getSodio() {
        return sodio;
    }

    public void setSodio(Short sodio) {
        this.sodio = sodio;
    }

    @Basic
    private Short potassio;

    public Short getPotassio() {
        return potassio;
    }

    public void setPotassio(Short potassio) {
        this.potassio = potassio;
    }

    @Basic
    private Short vitaminaA;

    public Short getVitaminaA() {
        return vitaminaA;
    }

    public void setVitaminaA(Short vitaminaA) {
        this.vitaminaA = vitaminaA;
    }

    @Basic
    private Short vitaminaC;

    public Short getVitaminaC() {
        return vitaminaC;
    }

    public void setVitaminaC(Short vitaminaC) {
        this.vitaminaC = vitaminaC;
    }

    @Basic
    private Short calcio;

    public Short getCalcio() {
        return calcio;
    }

    public void setCalcio(Short calcio) {
        this.calcio = calcio;
    }

    @Basic
    private Short ferro;

    public Short getFerro() {
        return ferro;
    }

    public void setFerro(Short ferro) {
        this.ferro = ferro;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInizio;

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataFine;

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    @Basic(optional = false)
    @Column(nullable = false,length = 16)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
