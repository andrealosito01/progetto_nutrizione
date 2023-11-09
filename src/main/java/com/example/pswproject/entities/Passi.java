package com.example.pswproject.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "passi")
public class Passi {

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
    private Integer valore;

    public Integer getValore() {
        return valore;
    }

    public void setValore(Integer valore) {
        this.valore = valore;
    }

}
