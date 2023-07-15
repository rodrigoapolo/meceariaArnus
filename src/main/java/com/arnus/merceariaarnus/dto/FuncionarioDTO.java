package com.arnus.merceariaarnus.dto;

import com.arnus.merceariaarnus.model.EnderecoModel;
import lombok.Data;

@Data
public class FuncionarioDTO {
    private String nome;
    private String telefone;
    private String email;
    private String clt;
    private EnderecoModel endereco;
}
