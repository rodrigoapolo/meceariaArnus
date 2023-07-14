package com.arnus.merceariaarnus.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_fornecedor")
public class FornecedorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Embedded
    private PessoaModel pessoaModel;
    private String cnpj;
}
