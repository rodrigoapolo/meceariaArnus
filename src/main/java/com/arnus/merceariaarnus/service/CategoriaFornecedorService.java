package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.CategoriaFornecedorDTO;
import com.arnus.merceariaarnus.dto.CategoriaProdutoDTO;
import com.arnus.merceariaarnus.model.CategoriaFornecedorModel;
import com.arnus.merceariaarnus.model.CategoriaProdutoModel;
import com.arnus.merceariaarnus.repository.CategoriaFornecedorRespository;
import com.arnus.merceariaarnus.repository.CategoriaProdutoRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaFornecedorService {
    @Autowired
    CategoriaFornecedorRespository fornecedorRespository;

    public CategoriaFornecedorDTO save(CategoriaFornecedorDTO categoriaDto){
        CategoriaFornecedorModel categoriaModel = new CategoriaFornecedorModel();
        BeanUtils.copyProperties(categoriaDto, categoriaModel);

        fornecedorRespository.save(categoriaModel);
        return categoriaDto;
    }
}
