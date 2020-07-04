package com.bri.restservice.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "List_Propinsi")
@Entity
public class Propinsi implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propinsi")
    private Integer idPropinsi;

    @Column(name = "nama_propinsi")
    private String namaPropinsi;

    public Propinsi() {
    }

    public Propinsi(Integer idPropinsi, String namaPropinsi) {
        this.idPropinsi = idPropinsi;
        this.namaPropinsi = namaPropinsi;
    }

    
    public Integer getIdPropinsi() {
        return idPropinsi;
    }

    public void setIdPropinsi(Integer idPropinsi) {
        this.idPropinsi = idPropinsi;
    }

    public String getNamaPropinsi() {
        return namaPropinsi;
    }

    public void setNamaPropinsi(String namaPropinsi) {
        this.namaPropinsi = namaPropinsi;
    }

    @Override
    public String toString() {
        return "Propinsi{" + "idPropinsi=" + idPropinsi + ", namaPropinsi=" + namaPropinsi + '}';
    }
    
}
    
    


