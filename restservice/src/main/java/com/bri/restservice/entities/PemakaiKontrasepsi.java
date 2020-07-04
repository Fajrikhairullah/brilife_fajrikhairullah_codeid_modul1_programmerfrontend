package com.bri.restservice.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "List_Pemakai_Kontrasepsi")
@Entity
public class PemakaiKontrasepsi implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_list")
    private Integer idList;
    
    @ManyToOne
    @JoinColumn(nullable = false) 
    private Propinsi propinsi;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Kontrasepsi kontrasepsi;
    
    @Column(name = "jumlah_pemakai")
    private Integer jumlahPemakai;

    public PemakaiKontrasepsi() {
    }

    public PemakaiKontrasepsi(Integer idList, Propinsi propinsi, Kontrasepsi kontrasepsi, Integer jumlahPemakai) {
        this.idList = idList;
        this.propinsi = propinsi;
        this.kontrasepsi = kontrasepsi;
        this.jumlahPemakai = jumlahPemakai;
    }

    public Propinsi getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(Propinsi propinsi) {
        this.propinsi = propinsi;
    }

    public Kontrasepsi getKontrasepsi() {
        return kontrasepsi;
    }

    public void setKontrasepsi(Kontrasepsi kontrasepsi) {
        this.kontrasepsi = kontrasepsi;
    }

    public Integer getJumlahPemakai() {
        return jumlahPemakai;
    }

    public void setJumlahPemakai(Integer jumlahPemakai) {
        this.jumlahPemakai = jumlahPemakai;
    }
            
    
    public Integer getIdList() {
        return idList;
    }

    public void setIdList(Integer idList) {
        this.idList = idList;
    }

    @Override
    public String toString() {
        return "PemakaiKontrasepsi{" + "idList=" + idList + ", propinsi=" + propinsi + ", kontrasepsi=" + kontrasepsi + ", jumlahPemakai=" + jumlahPemakai + '}';
    }
    
    
    
}
