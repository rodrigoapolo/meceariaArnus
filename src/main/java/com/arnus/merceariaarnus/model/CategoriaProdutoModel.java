package com.arnus.merceariaarnus.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_CategoriaProduto")
public class CategoriaProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
}
