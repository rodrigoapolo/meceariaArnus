package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.CategoriaProdutoDTO;
import com.arnus.merceariaarnus.model.CategoriaProdutoModel;
import com.arnus.merceariaarnus.repository.CategoriaProdutoRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaProdutoService {
    @Autowired
    CategoriaProdutoRespository categoriaProdutoRespository;

    public CategoriaProdutoModel findById(Integer id){
        if(id == 0)
            throw new IllegalArgumentException("ID da categoria Produto não pode ser 0");

        Optional <CategoriaProdutoModel> categoria = categoriaProdutoRespository.findByIdAndStatusTrue(id);
        if(!categoria.isPresent()) {
            throw new IllegalArgumentException("ID da categoria Produto não existe");
        }
        return categoria.get();
    }

    public CategoriaProdutoDTO salvar(CategoriaProdutoDTO categoriaDto){
        return update(null, categoriaDto);
    }

    public CategoriaProdutoDTO update( Integer id, CategoriaProdutoDTO categoriaDTO){
        CategoriaProdutoModel categoriaModel = new CategoriaProdutoModel();
        if(id != null){
            categoriaModel = findById(id);
        }

        BeanUtils.copyProperties(categoriaDTO, categoriaModel);

        categoriaModel.setStatus(true);
        categoriaProdutoRespository.save(categoriaModel);
        return categoriaDTO;
    }


    public void delete(Integer id){
        CategoriaProdutoModel categoria = findById(id);
        categoria.setStatus(false);
        categoriaProdutoRespository.save(categoria);
    }
}
