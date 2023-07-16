package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.dto.view.ClienteMaisCompra;
import com.arnus.merceariaarnus.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRespository extends JpaRepository<ClienteModel, Integer> {
    Optional<ClienteModel> findByIdAndStatusTrue(Integer id);

    @Query(value = "SELECT c.nome AS cliente, COUNT(p.id) AS totalCompras\n" +
            "FROM tb_cliente c\n" +
            "JOIN tb_pedido p ON c.id = p.cliente_id \n" +
            "GROUP BY c.nome \n" +
            "ORDER BY totalCompras DESC", nativeQuery = true)
    List<ClienteMaisCompra> consultarClientesMaisCompram();
}
