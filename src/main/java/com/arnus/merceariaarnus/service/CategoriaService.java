package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.CategoriaProdutoDTO;
import com.arnus.merceariaarnus.model.CategoriaModel;
import com.arnus.merceariaarnus.repository.CategoriaRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRespository categoriaRespository;

    public CategoriaModel findById(Integer id){
        if(id == 0)
            throw new IllegalArgumentException("ID da categoria Produto não pode ser 0");

        Optional <CategoriaModel> categoria = categoriaRespository.findByIdAndStatusTrue(id);
        if(!categoria.isPresent()) {
            throw new IllegalArgumentException("ID da categoria Produto não existe");
        }
        return categoria.get();
    }

    public CategoriaProdutoDTO salvar(CategoriaProdutoDTO categoriaDto){
        return update(null, categoriaDto);
    }

    public CategoriaProdutoDTO update( Integer id, CategoriaProdutoDTO categoriaDTO){
        CategoriaModel categoriaModel = new CategoriaModel();
        if(id != null){
            categoriaModel = findById(id);
        }

        BeanUtils.copyProperties(categoriaDTO, categoriaModel);

        categoriaModel.setStatus(true);
        categoriaRespository.save(categoriaModel);
        return categoriaDTO;
    }


    public void delete(Integer id){
        CategoriaModel categoria = findById(id);
        categoria.setStatus(false);
        categoriaRespository.save(categoria);
    }
}
