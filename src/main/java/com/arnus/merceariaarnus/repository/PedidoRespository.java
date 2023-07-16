package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.dto.view.TotalVenda;
import com.arnus.merceariaarnus.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRespository extends JpaRepository<PedidoModel, Integer> {

    @Query("SELECT COUNT(p.id) as totalVendas FROM PedidoModel p")
    TotalVenda getTotalVenda();
}
