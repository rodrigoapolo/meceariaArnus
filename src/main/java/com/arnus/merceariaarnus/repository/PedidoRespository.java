package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.dto.view.PedidoPorData;
import com.arnus.merceariaarnus.dto.view.TotalPedido;
import com.arnus.merceariaarnus.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRespository extends JpaRepository<PedidoModel, Integer> {

    @Query("SELECT COUNT(p.id) as totalVendas FROM PedidoModel p")
    TotalPedido consultarTotalPedido();

    @Query(value = "SELECT p.data, c.nome AS cliente, f.nome AS funcionario, p.total\n" +
            "FROM tb_pedido p \n" +
            "INNER JOIN tb_cliente c ON p.cliente_id = c.id\n" +
            "INNER JOIN tb_funcionario  f ON p.funcionario_id = f.id\n" +
            "WHERE p.data BETWEEN :inicio AND :fim", nativeQuery = true)
    List<PedidoPorData> consultarPedidosPorPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
