package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.model.CategoriaModel;
import com.arnus.merceariaarnus.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRespository extends JpaRepository<CategoriaModel, Integer> {
}
