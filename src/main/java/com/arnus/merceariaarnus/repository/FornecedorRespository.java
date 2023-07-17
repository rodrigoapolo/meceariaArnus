package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.model.ClienteModel;
import com.arnus.merceariaarnus.model.FornecedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRespository extends JpaRepository<FornecedorModel, Integer> {
    Optional<FornecedorModel> findByIdAndStatusTrue(Integer id);
}
