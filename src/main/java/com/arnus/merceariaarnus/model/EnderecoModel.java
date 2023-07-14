package com.arnus.merceariaarnus.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class EnderecoModel {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
}

