package com.bri.restservice.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;

@ApiModel(value = "Propinsi")

public class PropinsiModel {
    
    @ApiModelProperty(value = "Propinsi ID")
    private Integer idPropinsi;
    
    @NotBlank(message = "{name.notblank}")
    private String namaPropinsi;

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
        return "PropinsiModel{" + "idPropinsi=" + idPropinsi + ", namaPropinsi=" + namaPropinsi + '}';
    }
    
    
    
}
