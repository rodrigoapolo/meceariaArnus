package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.model.ClienteModel;
import com.arnus.merceariaarnus.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRespository extends JpaRepository<ProdutoModel, Integer> {
    Optional<ProdutoModel> findByIdAndStatusTrue(Integer id);
}
