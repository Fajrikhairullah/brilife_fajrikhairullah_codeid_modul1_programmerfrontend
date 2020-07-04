package com.bri.restservice.models;

import com.bri.restservice.entities.Kontrasepsi;
import com.bri.restservice.entities.Propinsi;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Pemakai_Kontrasepsi")
public class PemakaiKontrasepsiModel {
    
    @ApiModelProperty(value = "Pemakai Kontrasepsi ID")
    private Integer idList;
    
    private Propinsi propinsi;
    
    private Kontrasepsi kontrasepsi;
    
    private Integer jumlahPemakai;

    public Integer getIdList() {
        return idList;
    }

    public void setIdList(Integer idList) {
        this.idList = idList;
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

    @Override
    public String toString() {
        return "PemakaiKontrasepsiModel{" + "idList=" + idList + ", propinsi=" + propinsi + ", kontrasepsi=" + kontrasepsi + ", jumlahPemakai=" + jumlahPemakai + '}';
    }

    
    
    
    
}
