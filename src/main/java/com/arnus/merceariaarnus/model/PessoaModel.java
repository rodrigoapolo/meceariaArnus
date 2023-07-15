package com.arnus.merceariaarnus.model;

import lombok.Data;

@Data
public class PessoaModel {
    private String nome;
    private String telefone;
    private String email;
    private EnderecoModel endereco;
}
