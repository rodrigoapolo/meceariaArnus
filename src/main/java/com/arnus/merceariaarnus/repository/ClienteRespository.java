package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRespository extends JpaRepository<ClienteModel, Integer> {
    Optional<ClienteModel> findByIdAndStatusTrue(Integer id);
}
