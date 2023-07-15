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

    public CategoriaFornecedorDTO save(CategoriaFornecedorDTO categoriaDto){
        return update(null,categoriaDto);
    }

    public CategoriaFornecedorDTO update(Integer id, CategoriaFornecedorDTO categoriaDTO){
        CategoriaFornecedorModel categoriaModel = new CategoriaFornecedorModel();
        if(id != null){
            if(id == 0)
                throw new IllegalArgumentException("ID da categoria fornecedor não pode ser 0");
            if(!categoriaFornecedorRespository.findById(id).isPresent())
                throw new IllegalArgumentException("ID da categoria fornecedor não existe");

            categoriaModel = categoriaFornecedorRespository.getReferenceById(id);
        }

        BeanUtils.copyProperties(categoriaDTO, categoriaModel);

        categoriaFornecedorRespository.save(categoriaModel);

        return categoriaDTO;
    }
}
