package com.example.pswproject.entities;

import javax.persistence.*;

@Entity
@Table(name="piani")
public class Piano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() { return id; }

    @Basic(optional = false)
    @Column(length = 32, nullable = false)
    private String nome;

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short energia;

    public Short getEnergia() { return energia; }

    public void setEnergia(Short energia) { this.energia = energia; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short proteine;

    public Short getProteine() { return proteine; }

    public void setProteine(Short proteine) { this.proteine = proteine; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short carboidrati;

    public Short getCarboidrati() { return carboidrati; }

    public void setCarboidrati(Short carboidrati) { this.carboidrati = carboidrati; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short fibre;

    public Short getFibre() { return fibre; }

    public void setFibre(Short fibre) { this.fibre = fibre; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short zuccheri;

    public Short getZuccheri() { return zuccheri; }

    public void setZuccheri(Short zuccheri) { this.zuccheri = zuccheri; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short grassiTotali;

    public Short getGrassiTotali() { return grassiTotali; }

    public void setGrassiTotali(Short grassiTotali) { this.grassiTotali = grassiTotali; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short grassiSaturi;

    public Short getGrassiSaturi() { return grassiSaturi; }

    public void setGrassiSaturi(Short grassiSaturi) { this.grassiSaturi = grassiSaturi; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short grassiPolinsaturi;

    public Short getGrassiPolinsaturi() { return grassiPolinsaturi; }

    public void setGrassiPolinsaturi(Short grassiPolinsaturi) { this.grassiPolinsaturi = grassiPolinsaturi; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short grassiMonoinsaturi;

    public Short getGrassiMonoinsaturi() { return grassiMonoinsaturi; }

    public void setGrassiMonoinsaturi(Short grassiMonoinsaturi) { this.grassiMonoinsaturi = grassiMonoinsaturi; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short grassiTrans;

    public Short getGrassiTrans() { return grassiTrans; }

    public void setGrassiTrans(Short grassiTrans) { this.grassiTrans = grassiTrans; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short colesterolo;

    public Short getColesterolo() { return colesterolo; }

    public void setColesterolo(Short colesterolo) { this.colesterolo = colesterolo; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short sodio;

    public Short getSodio() { return sodio; }

    public void setSodio(Short sodio) { this.sodio = sodio; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short potassio;

    public Short getPotassio() { return potassio; }

    public void setPotassio(Short potassio) { this.potassio = potassio; }

    @Basic(optional = false)
    @Column(name="vitamina_a",nullable = false)
    private Short vitaminaA;

    public Short getVitaminaA() { return vitaminaA; }

    public void setVitaminaA(Short vitaminaA) { this.vitaminaA = vitaminaA; }

    @Basic(optional = false)
    @Column(name="vitamina_c",nullable = false)
    private Short vitaminaC;

    public Short getVitaminaC() { return vitaminaC; }

    public void setVitaminaC(Short vitaminaC) { this.vitaminaC = vitaminaC; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short calcio;

    public Short getCalcio() { return calcio; }

    public void setCalcio(Short calcio) { this.calcio = calcio; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short ferro;

    public Short getFerro() { return ferro; }

    public void setFerro(Short ferro) { this.ferro = ferro; }


}
