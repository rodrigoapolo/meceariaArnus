package com.arnus.merceariaarnus.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_fornecedor")
public class FornecedorModel {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Embedded
    private PessoaModel pessoaModel;
    private String cnpj;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_fornecedor_id", referencedColumnName = "id")
    CategoriaFornecedorModel categoriaFornecedor;
}
