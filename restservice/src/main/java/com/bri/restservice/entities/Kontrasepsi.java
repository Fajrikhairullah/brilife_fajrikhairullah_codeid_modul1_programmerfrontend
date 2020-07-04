package com.bri.restservice.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "List_Kontrasepsi")
@Entity
public class Kontrasepsi implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kontrasepsi")
    private Integer idKontrasepsi;
    
    @Column(name = "nama_kontrasepsi")
    private String namaKontrasepsi;

    public Kontrasepsi(Integer idKontrasepsi, String namaKontrasepsi) {
        this.idKontrasepsi = idKontrasepsi;
        this.namaKontrasepsi = namaKontrasepsi;
    }
    public String getNamaKontrasepsi() {
        return namaKontrasepsi;
    }

    public void setNamaKontrasepsi(String namaKontrasepsi) {
        this.namaKontrasepsi = namaKontrasepsi;
    }

    public Integer getIdKontrasepsi() {
        return idKontrasepsi;
    }

    public void setIdKontrasepsi(Integer idKontrasepsi) {
        this.idKontrasepsi = idKontrasepsi;
    }

    @Override
    public String toString() {
        return "Kontrasepsi{" + "idKontrasepsi=" + idKontrasepsi + ", namaKontrasepsi=" + namaKontrasepsi + '}';
    }
    
    
    
    
}
