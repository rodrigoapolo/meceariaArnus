package com.arnus.merceariaarnus.dto;

import lombok.Data;

@Data
public class ProdutoDTO {
    private String nome;
    private Double preco;
    private Integer qtd;
    private Integer categoriaProduto;
    private Integer fornecedor;
}
