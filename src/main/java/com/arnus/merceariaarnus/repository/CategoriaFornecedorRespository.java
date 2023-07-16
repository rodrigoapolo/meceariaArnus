package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.model.CategoriaFornecedorModel;
import com.arnus.merceariaarnus.model.CategoriaProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaFornecedorRespository extends JpaRepository<CategoriaFornecedorModel, Integer> {
    Optional<CategoriaFornecedorModel> findByIdAndStatusTrue(Integer id);
}
