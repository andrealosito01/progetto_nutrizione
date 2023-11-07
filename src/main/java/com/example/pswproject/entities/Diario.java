package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="diari")
public class Diario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() { return id; }

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

    @Basic
    private Double acqua = (double) 0;

    public Double getAcqua() { return acqua; }

    public void setAcqua(Double acqua) { this.acqua = acqua; }

    @Basic
    private Integer passi = 0;

    public Integer getPassi() { return passi; }

    public void setPassi(Integer passi) { this.passi = passi; }

    @OneToMany
    @JoinColumn(name="diario_id")
    private List<VoceDiario> vociDiario = new ArrayList<>();

    public List<VoceDiario> getVociDiario() { return vociDiario; }

    public void setVociDiario(List<VoceDiario> vociDiario) { this.vociDiario = vociDiario; }

}
