package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.ProdutoDTO;
import com.arnus.merceariaarnus.model.ProdutoModel;
import com.arnus.merceariaarnus.repository.ProdutoRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRespository produtoRespository;
    @Autowired
    CategoriaProdutoService categoriaProdutoService;
    @Autowired
    FornecedorService fornecedorService;

    public ProdutoModel findById(Integer id){
        if (id == 0)
            throw new IllegalArgumentException("ID do funcionario não pode ser 0");

        Optional<ProdutoModel> produto =  produtoRespository.findByIdAndStatusTrue(id);
        if(!produto.isPresent())
            throw new IllegalArgumentException("ID do Produto não existe");

        return produto.get();
    }

    public ProdutoDTO salvar(ProdutoDTO produtoDTO){
        return update(null, produtoDTO);
    }

    public ProdutoDTO update(Integer id, ProdutoDTO produtoDTO){
        ProdutoModel produtoModel = new ProdutoModel();
        if(id != null) {
            produtoModel = findById(id);
        }

        produtoModel.setCategoriaProdutoModel(categoriaProdutoService.findById(produtoDTO.getCategoriaProduto()));
        produtoModel.setFornecedorModel(fornecedorService.findById(produtoDTO.getFornecedor()));
        BeanUtils.copyProperties(produtoDTO, produtoModel);

        produtoModel.setStatus(true);
        produtoRespository.save(produtoModel);

        return produtoDTO;
    }

    public void delete(Integer id){
        ProdutoModel produto = findById(id);
        produto.setStatus(false);
        produtoRespository.save(produto);
    }

}
