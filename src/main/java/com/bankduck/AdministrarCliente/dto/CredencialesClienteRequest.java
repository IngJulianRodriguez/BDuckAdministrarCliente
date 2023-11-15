package com.bankduck.AdministrarCliente.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

@ApiModel()
public class CredencialesClienteRequest {

    @ApiModelProperty(name = "cedula", required = true,example = "", value = "")
    private Long cedula;
    @ApiModelProperty(name = "contrasena", required = true,example = "", value = "")
    private String contrasena;

    public CredencialesClienteRequest() {
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }
}
