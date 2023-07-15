package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.model.CategoriaFornecedorModel;
import com.arnus.merceariaarnus.model.CategoriaProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaFornecedorRespository extends JpaRepository<CategoriaFornecedorModel, Integer> {
}
