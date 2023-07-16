package com.arnus.merceariaarnus.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PedidoDTO {

    private Integer cliente;
    private Integer Funcionario;
    private LocalDate data;
    private List<InterPedidoDTO> interPedidoDTO;
}
