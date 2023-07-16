package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.model.InterPedidoModel;
import com.arnus.merceariaarnus.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterPedidoRespository extends JpaRepository<InterPedidoModel, Integer> {
}