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
    private double proteine;

    public double getProteine() { return proteine; }

    public void setProteine(double proteine) { this.proteine = proteine; }

    @Basic(optional = false)
    @Column(nullable = false)
    private double carboidrati;

    public double getCarboidrati() { return carboidrati; }

    public void setCarboidrati(double carboidrati) { this.carboidrati = carboidrati; }

    @Basic(optional = false)
    @Column(nullable = false)
    private double fibre;

    public double getFibre() { return fibre; }

    public void setFibre(double fibre) { this.fibre = fibre; }

    @Basic(optional = false)
    @Column(nullable = false)
    private double zuccheri;

    public double getZuccheri() { return zuccheri; }

    public void setZuccheri(double zuccheri) { this.zuccheri = zuccheri; }

    @Basic(optional = false)
    @Column(nullable = false)
    private double grassiTotali;

    public double getGrassiTotali() { return grassiTotali; }

    public void setGrassiTotali(double grassiTotali) { this.grassiTotali = grassiTotali; }

    @Basic(optional = false)
    @Column(nullable = false)
    private double grassiSaturi;

    public double getGrassiSaturi() { return grassiSaturi; }

    public void setGrassiSaturi(double grassiSaturi) { this.grassiSaturi = grassiSaturi; }

    @Basic(optional = false)
    @Column(nullable = false)
    private double grassiPolinsaturi;

    public double getGrassiPolinsaturi() { return grassiPolinsaturi; }

    public void setGrassiPolinsaturi(double grassiPolinsaturi) { this.grassiPolinsaturi = grassiPolinsaturi; }

    @Basic(optional = false)
    @Column(nullable = false)
    private double grassiMonoinsaturi;

    public double getGrassiMonoinsaturi() { return grassiMonoinsaturi; }

    public void setGrassiMonoinsaturi(double grassiMonoinsaturi) { this.grassiMonoinsaturi = grassiMonoinsaturi; }

    @Basic(optional = false)
    @Column(nullable = false)
    private double grassiTrans;

    public double getGrassiTrans() { return grassiTrans; }

    public void setGrassiTrans(double grassiTrans) { this.grassiTrans = grassiTrans; }

    @Basic(optional = false)
    @Column(nullable = false)
    private double colesterolo;

    public double getColesterolo() { return colesterolo; }

    public void setColesterolo(double colesterolo) { this.colesterolo = colesterolo; }

    @Basic(optional = false)
    @Column(nullable = false)
    private double sodio;

    public double getSodio() { return sodio; }

    public void setSodio(double sodio) { this.sodio = sodio; }

    @Basic(optional = false)
    @Column(nullable = false)
    private double potassio;

    public double getPotassio() { return potassio; }

    public void setPotassio(double potassio) { this.potassio = potassio; }

    @Basic(optional = false)
    @Column(name="vitamina_a",nullable = false)
    private double vitaminaA;

    public double getVitaminaA() { return vitaminaA; }

    public void setVitaminaA(double vitaminaA) { this.vitaminaA = vitaminaA; }

    @Basic(optional = false)
    @Column(name="vitamina_c",nullable = false)
    private double vitaminaC;

    public double getVitaminaC() { return vitaminaC; }

    public void setVitaminaC(double vitaminaC) { this.vitaminaC = vitaminaC; }

    @Basic(optional = false)
    @Column(nullable = false)
    private double calcio;

    public double getCalcio() { return calcio; }

    public void setCalcio(double calcio) { this.calcio = calcio; }

    @Basic(optional = false)
    @Column(nullable = false)
    private double ferro;

    public double getFerro() { return ferro; }

    public void setFerro(double ferro) { this.ferro = ferro; }


}
