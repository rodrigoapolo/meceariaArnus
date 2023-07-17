package com.arnus.merceariaarnus.repository;

import com.arnus.merceariaarnus.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRespository extends JpaRepository<CategoriaModel, Integer> {
    Optional<CategoriaModel> findByIdAndStatusTrue(Integer id);
}
