package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.CategoriaProdutoDTO;
import com.arnus.merceariaarnus.model.CategoriaProdutoModel;
import com.arnus.merceariaarnus.repository.CategoriaProdutoRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaProdutoService {
    @Autowired
    CategoriaProdutoRespository categoriaProdutoRespository;

    public CategoriaProdutoDTO save(CategoriaProdutoDTO categoriaDto){
        CategoriaProdutoModel categoriaModel = new CategoriaProdutoModel();
        BeanUtils.copyProperties(categoriaDto, categoriaModel);
        categoriaProdutoRespository.save(categoriaModel);

        return categoriaDto;
    }

    public CategoriaProdutoDTO update( Integer id, CategoriaProdutoDTO categoriaDTO){
        if(!categoriaProdutoRespository.findById(id).isPresent())
            throw new IllegalArgumentException("ID da categoria Produto não existe");

        CategoriaProdutoModel categoriaModel = categoriaProdutoRespository.getReferenceById(id);
        BeanUtils.copyProperties(categoriaDTO, categoriaModel);

        categoriaProdutoRespository.save(categoriaModel);

        return categoriaDTO;
    }
}
