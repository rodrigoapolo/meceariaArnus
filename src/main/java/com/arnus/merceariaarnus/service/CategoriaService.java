package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.CategoriaProdutoDTO;
import com.arnus.merceariaarnus.model.CategoriaProdutoModel;
import com.arnus.merceariaarnus.repository.CategoriaRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRespository categoriaRespository;

    public CategoriaProdutoDTO save(CategoriaProdutoDTO categoriaDto){
        CategoriaProdutoModel categoriaModel = new CategoriaProdutoModel();
        BeanUtils.copyProperties(categoriaDto, categoriaModel);
        categoriaRespository.save(categoriaModel);

        return categoriaDto;
    }
}
