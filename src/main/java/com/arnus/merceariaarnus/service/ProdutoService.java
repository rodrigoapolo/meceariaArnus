package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.ProdutoDTO;
import com.arnus.merceariaarnus.model.FuncionarioModel;
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
        ProdutoModel produtoModel = new ProdutoModel();
        if(id != null) {
            verificarProduto(id);

            produtoModel = produtoRespository.findByIdAndStatusTrue(id).get();
        }

        verificarIdCategoriaIdFornecedor(produtoDTO);

        BeanUtils.copyProperties(produtoDTO, produtoModel);
        produtoModel.setCategoriaProdutoModel(categoriaProdutoRespository.findByIdAndStatusTrue(produtoDTO.getCategoriaProduto()).get());
        produtoModel.setFornecedorModel(fornecedorRespository.findByIdAndStatusTrue(produtoDTO.getFornecedor()).get());

        produtoModel.setStatus(true);
        produtoRespository.save(produtoModel);

        return produtoDTO;
    }

    private void verificarProduto(Integer id) {
        if (id == 0)
            throw new IllegalArgumentException("ID do funcionario não pode ser 0");

        if(!produtoRespository.findByIdAndStatusTrue(id).isPresent())
            throw new IllegalArgumentException("ID do Produto não existe");
    }

    private void verificarIdCategoriaIdFornecedor(ProdutoDTO produtoDTO) {
        if(!categoriaProdutoRespository.findByIdAndStatusTrue(produtoDTO.getCategoriaProduto()).isPresent())
            throw new IllegalArgumentException("ID da categoria Produto não existe");

        if(!fornecedorRespository.findByIdAndStatusTrue(produtoDTO.getFornecedor()).isPresent())
            throw new IllegalArgumentException("ID do fornecedor não existe");
    }

    public void delete(Integer id){
        verificarProduto(id);
        ProdutoModel produto = produtoRespository.findByIdAndStatusTrue(id).get();
        produto.setStatus(false);
        produtoRespository.save(produto);
    }

}
