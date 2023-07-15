package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.CategoriaProdutoDTO;
import com.arnus.merceariaarnus.dto.ProdutoDTO;
import com.arnus.merceariaarnus.model.CategoriaProdutoModel;
import com.arnus.merceariaarnus.model.ProdutoModel;
import com.arnus.merceariaarnus.repository.CategoriaProdutoRespository;
import com.arnus.merceariaarnus.repository.FornecedorRespository;
import com.arnus.merceariaarnus.repository.ProdutoRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRespository produtoRespository;
    @Autowired
    CategoriaProdutoRespository categoriaProdutoRespository;
    @Autowired
    FornecedorRespository fornecedorRespository;


    public ProdutoDTO salvar(ProdutoDTO produtoDTO){
        return update(null, produtoDTO);
    }

    public ProdutoDTO update(Integer id, ProdutoDTO produtoDTO){
        ProdutoModel categoriaModel = new ProdutoModel();
        if(id != null) {
            if (id == 0)
                throw new IllegalArgumentException("ID do funcionario não pode ser 0");

            if(!produtoRespository.findById(id).isPresent())
                throw new IllegalArgumentException("ID do Produto não existe");

            categoriaModel = produtoRespository.getReferenceById(id);
        }

        verificarProduto(produtoDTO);

        BeanUtils.copyProperties(produtoDTO, categoriaModel);
        categoriaModel.setCategoriaProdutoModel(categoriaProdutoRespository.findById(produtoDTO.getCategoriaProduto()).get());
        categoriaModel.setFornecedorModel(fornecedorRespository.findById(produtoDTO.getFornecedor()).get());

        produtoRespository.save(categoriaModel);

        return produtoDTO;
    }

    private void verificarProduto(ProdutoDTO produtoDTO) {
        if(!categoriaProdutoRespository.findById(produtoDTO.getCategoriaProduto()).isPresent())
            throw new IllegalArgumentException("ID da categoria Produto não existe");

        if(!fornecedorRespository.findById(produtoDTO.getFornecedor()).isPresent())
            throw new IllegalArgumentException("ID do fornecedor não existe");
    }

}
