package com.arnus.merceariaarnus.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_Produto")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;
    private Integer qtd;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_produto_id", referencedColumnName = "id")
    private CategoriaProdutoModel categoriaProdutoModel;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
    private FornecedorModel fornecedorModel;
}
