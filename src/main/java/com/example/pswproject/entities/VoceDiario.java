package com.example.pswproject.entities;

import javax.persistence.*;

@Entity
@Table(name="voci")
public class VoceDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() { return id; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Double quantita;

    public Double getQuantita() { return quantita; }

    public void setQuantita(Double quantita) { this.quantita = quantita; }

    @Basic(optional = false)
    @Column(length = 9, nullable = false)
    private String pasto;

    public String getPasto() { return pasto; }

    public void setPasto(String pasto) { this.pasto = pasto; }

    @Basic(optional = false)
    @Column(length = 64, nullable = false)
    private String nome;

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome=nome; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Short energia;

    public Short getEnergia() { return energia; }

    public void setEnergia(Short energia) { this.energia = energia; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Double proteine;

    public Double getProteine() { return proteine; }

    public void setProteine(Double proteine) { this.proteine = proteine; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Double carboidrati;

    public Double getCarboidrati() { return carboidrati; }

    public void setCarboidrati(Double carboidrati) { this.carboidrati = carboidrati; }

    @Basic
    private Double fibre;

    public Double getFibre() { return fibre; }

    public void setFibre(Double fibre) { this.fibre = fibre; }

    @Basic
    private Double zuccheri;

    public Double getZuccheri() { return zuccheri; }

    public void setZuccheri(Double zuccheri) { this.zuccheri = zuccheri; }

    @Basic(optional = false)
    @Column(nullable = false)
    private Double grassiTotali;

    public Double getGrassiTotali() { return grassiTotali; }

    public void setGrassiTotali(Double grassiTotali) { this.grassiTotali = grassiTotali; }

    @Basic
    private Double grassiSaturi;

    public Double getGrassiSaturi() { return grassiSaturi; }

    public void setGrassiSaturi(Double grassiSaturi) { this.grassiSaturi = grassiSaturi; }

    @Basic
    private Double grassiPolinsaturi;

    public Double getGrassiPolinsaturi() { return grassiPolinsaturi; }

    public void setGrassiPolinsaturi(Double grassiPolinsaturi) { this.grassiPolinsaturi = grassiPolinsaturi; }

    @Basic
    private Double grassiMonoinsaturi;

    public Double getGrassiMonoinsaturi() { return grassiMonoinsaturi; }

    public void setGrassiMonoinsaturi(Double grassiMonoinsaturi) { this.grassiMonoinsaturi = grassiMonoinsaturi; }

    @Basic
    private Double grassiTrans;

    public Double getGrassiTrans() { return grassiTrans; }

    public void setGrassiTrans(Double grassiTrans) { this.grassiTrans = grassiTrans; }

    @Basic
    private Short colesterolo;

    public Short getColesterolo() { return colesterolo; }

    public void setColesterolo(Short colesterolo) { this.colesterolo = colesterolo; }

    @Basic
    private Short sodio;

    public Short getSodio() { return sodio; }

    public void setSodio(Short sodio) { this.sodio = sodio; }

    @Basic
    private Short potassio;

    public Short getPotassio() { return potassio; }

    public void setPotassio(Short potassio) { this.potassio = potassio; }

    @Basic
    @Column(name="vitamina_a")
    private Short vitaminaA;

    public Short getVitaminaA() { return vitaminaA; }

    public void setVitaminaA(Short vitaminaA) { this.vitaminaA = vitaminaA; }

    @Basic
    @Column(name="vitamina_c")
    private Short vitaminaC;

    public Short getVitaminaC() { return vitaminaC; }

    public void setVitaminaC(Short vitaminaC) { this.vitaminaC = vitaminaC; }

    @Basic
    private Short calcio;

    public Short getCalcio() { return calcio; }

    public void setCalcio(Short calcio) { this.calcio = calcio; }

    @Basic
    private Short ferro;

    public Short getFerro() { return ferro; }

    public void setFerro(Short ferro) { this.ferro = ferro; }

}
