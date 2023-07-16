package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRespository extends JpaRepository<PedidoModel, Integer> {
}
