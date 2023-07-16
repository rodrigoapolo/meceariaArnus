package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.CategoriaFornecedorDTO;
import com.arnus.merceariaarnus.model.CategoriaFornecedorModel;
import com.arnus.merceariaarnus.repository.CategoriaFornecedorRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaFornecedorService {
    @Autowired
    CategoriaFornecedorRespository categoriaFornecedorRespository;

    public CategoriaFornecedorDTO salvar(CategoriaFornecedorDTO categoriaDto){
        return update(null,categoriaDto);
    }

    public CategoriaFornecedorDTO update(Integer id, CategoriaFornecedorDTO categoriaDTO){
        CategoriaFornecedorModel categoriaModel = new CategoriaFornecedorModel();
        if(id != null){
            verificarCategoria(id);

            categoriaModel = categoriaFornecedorRespository.findByIdAndStatusTrue(id).get();
        }

        BeanUtils.copyProperties(categoriaDTO, categoriaModel);
        categoriaModel.setStatus(true);
        categoriaFornecedorRespository.save(categoriaModel);

        return categoriaDTO;
    }

    private void verificarCategoria(Integer id) {
        if(id == 0)
            throw new IllegalArgumentException("ID da categoria fornecedor não pode ser 0");
        if(!categoriaFornecedorRespository.findByIdAndStatusTrue(id).isPresent())
            throw new IllegalArgumentException("ID da categoria fornecedor não existe");
    }

    public void delete(Integer id){
        verificarCategoria(id);
        CategoriaFornecedorModel categoria = categoriaFornecedorRespository.findByIdAndStatusTrue(id).get();
        categoria.setStatus(false);
        categoriaFornecedorRespository.save(categoria);
    }
}
