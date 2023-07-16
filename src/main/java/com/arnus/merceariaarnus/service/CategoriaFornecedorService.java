package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.CategoriaFornecedorDTO;
import com.arnus.merceariaarnus.model.CategoriaFornecedorModel;
import com.arnus.merceariaarnus.repository.CategoriaFornecedorRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaFornecedorService {
    @Autowired
    CategoriaFornecedorRespository categoriaFornecedorRespository;

    public CategoriaFornecedorModel findById(Integer id){
        if(id == 0)
            throw new IllegalArgumentException("ID da categoria fornecedor não pode ser 0");

        Optional<CategoriaFornecedorModel> categoria = categoriaFornecedorRespository.findByIdAndStatusTrue(id);
        if(!categoria.isPresent())
            throw new IllegalArgumentException("ID da categoria fornecedor não existe");
        return categoria.get();
    }
    public CategoriaFornecedorDTO salvar(CategoriaFornecedorDTO categoriaDto){
        return update(null,categoriaDto);
    }

    public CategoriaFornecedorDTO update(Integer id, CategoriaFornecedorDTO categoriaDTO){
        CategoriaFornecedorModel categoriaModel = new CategoriaFornecedorModel();
        if(id != null){
            categoriaModel = findById(id);
        }

        BeanUtils.copyProperties(categoriaDTO, categoriaModel);
        categoriaModel.setStatus(true);
        categoriaFornecedorRespository.save(categoriaModel);

        return categoriaDTO;
    }

    public void delete(Integer id){
        CategoriaFornecedorModel categoria = findById(id);
        categoria.setStatus(false);
        categoriaFornecedorRespository.save(categoria);
    }
}
