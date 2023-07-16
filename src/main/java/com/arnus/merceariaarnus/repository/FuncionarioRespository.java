package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.model.ClienteModel;
import com.arnus.merceariaarnus.model.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRespository extends JpaRepository<FuncionarioModel, Integer> {
    Optional<FuncionarioModel> findByIdAndStatusTrue(Integer id);
}
