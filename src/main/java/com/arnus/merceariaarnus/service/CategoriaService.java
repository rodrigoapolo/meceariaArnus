package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.model.CategoriaProdutoModel;
import com.arnus.merceariaarnus.repository.CategoriaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRespository categoriaRespository;

    public CategoriaProdutoModel save(String catego){
        return new CategoriaProdutoModel();
    }
}
