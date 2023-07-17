package com.arnus.merceariaarnus.dto;


import com.arnus.merceariaarnus.model.EnderecoModel;
import lombok.Data;

@Data
public class FornecedorDTO {
    private String nome;
    private String telefone;
    private String email;
    private String cnpj;
    private Integer categoria;
    private EnderecoModel endereco;
}
