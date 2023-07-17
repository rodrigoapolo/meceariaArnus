package com.arnus.merceariaarnus.dto.view;

import java.time.LocalDate;

public interface PedidoPorData {
    LocalDate getData();
    String getCliente();
    String getFuncionario();
    Double getTotal();
}