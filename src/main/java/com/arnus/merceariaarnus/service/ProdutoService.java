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
        ProdutoModel produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDTO, produtoModel);

        verificarProduto(produtoDTO);

        produtoModel.setCategoriaProdutoModel(categoriaProdutoRespository.findById(produtoDTO.getCategoriaProduto()).get());
        produtoModel.setFornecedorModel(fornecedorRespository.findById(produtoDTO.getFornecedor()).get());

        produtoRespository.save(produtoModel);

        return produtoDTO;
    }

    public ProdutoDTO update(Integer id, ProdutoDTO produtoDTO){
        if(!produtoRespository.findById(id).isPresent())
            throw new IllegalArgumentException("ID do Produto não existe");

       verificarProduto(produtoDTO);

        ProdutoModel categoriaModel = produtoRespository.getReferenceById(id);
        BeanUtils.copyProperties(produtoDTO, categoriaModel);

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
