package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.model.CategoriaProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaProdutoRespository extends JpaRepository<CategoriaProdutoModel, Integer> {
    Optional<CategoriaProdutoModel> findByIdAndStatusTrue(Integer id);
}
