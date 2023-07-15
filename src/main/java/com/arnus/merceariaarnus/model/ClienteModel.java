package com.arnus.merceariaarnus.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@Table(name = "tb_Cliente")
public class ClienteModel {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Embedded
    private PessoaModel pessoaModel;
    private String cpf;
    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id",referencedColumnName = "id")
    private List<PedidoModel> pedidoModels = new LinkedList<>();
}
