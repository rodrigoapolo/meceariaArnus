package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.CategoriaProdutoDTO;
import com.arnus.merceariaarnus.dto.ProdutoDTO;
import com.arnus.merceariaarnus.model.CategoriaFornecedorModel;
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
    public CategoriaProdutoDTO salvar(CategoriaProdutoDTO categoriaDto){
        return update(null, categoriaDto);
    }

    public CategoriaProdutoDTO update( Integer id, CategoriaProdutoDTO categoriaDTO){
        CategoriaProdutoModel categoriaModel = new CategoriaProdutoModel();
        if(id != null){
            verificarCategoria(id);

            categoriaModel = categoriaProdutoRespository.findByIdAndStatusTrue(id).get();
        }

        BeanUtils.copyProperties(categoriaDTO, categoriaModel);

        categoriaModel.setStatus(true);
        categoriaProdutoRespository.save(categoriaModel);
        return categoriaDTO;
    }

    private void verificarCategoria(Integer id) {
        if(id == 0)
            throw new IllegalArgumentException("ID da categoria Produto não pode ser 0");
        if(!categoriaProdutoRespository.findByIdAndStatusTrue(id).isPresent())
            throw new IllegalArgumentException("ID da categoria Produto não existe");
    }

    public void delete(Integer id){
        verificarCategoria(id);
        CategoriaProdutoModel categoria = categoriaProdutoRespository.findByIdAndStatusTrue(id).get();
        categoria.setStatus(false);
        categoriaProdutoRespository.save(categoria);
    }
}
