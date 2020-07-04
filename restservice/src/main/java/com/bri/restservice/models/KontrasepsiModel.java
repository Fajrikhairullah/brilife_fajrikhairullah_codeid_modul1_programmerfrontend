package com.bri.restservice.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Kontrasepsi")
public class KontrasepsiModel {
    
    @ApiModelProperty(value = "Kontrasepsi ID")
    private Integer idKontrasepsi;
    
    private String namaKontrasepsi;

    public Integer getIdKontrasepsi() {
        return idKontrasepsi;
    }

    public void setIdKontrasepsi(Integer idKontrasepsi) {
        this.idKontrasepsi = idKontrasepsi;
    }

    public String getNamaKontrasepsi() {
        return namaKontrasepsi;
    }

    public void setNamaKontrasepsi(String namaKontrasepsi) {
        this.namaKontrasepsi = namaKontrasepsi;
    }

    @Override
    public String toString() {
        return "KontrasepsiModel{" + "idKontrasepsi=" + idKontrasepsi + ", namaKontrasepsi=" + namaKontrasepsi + '}';
    }
    
    
}
