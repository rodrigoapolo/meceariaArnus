package com.arnus.merceariaarnus.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_CategoriaFornecedor")
public class CategoriaFornecedorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
}
