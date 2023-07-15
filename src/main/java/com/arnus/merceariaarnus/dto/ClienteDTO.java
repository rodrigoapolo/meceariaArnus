package com.arnus.merceariaarnus.dto;


import com.arnus.merceariaarnus.model.EnderecoModel;
import lombok.Data;

@Data
public class ClienteDTO {
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private EnderecoModel getEndereco;
}
