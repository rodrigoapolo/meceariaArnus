package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.ProdutoDTO;
import com.arnus.merceariaarnus.dto.view.ProdutosMiasVendidos;
import com.arnus.merceariaarnus.dto.view.TotalPedido;
import com.arnus.merceariaarnus.model.ProdutoModel;
import com.arnus.merceariaarnus.repository.ProdutoRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Integer diminuirValorEstoque(Integer qtd, ProdutoModel produtoModel){
        if(produtoModel.getQtd() >= qtd){
            produtoModel.setQtd(produtoModel.getQtd() - qtd);
            produtoRespository.save(produtoModel);
        }else {
            throw new IllegalArgumentException("Estoque indisponivel para essa qualidade: "+qtd);
        }

        if(produtoModel.getQtd() < 300){
            //TODO disparar um email
        }

        return qtd;
    }

    public List<ProdutosMiasVendidos> consultarProdutosMaisVendios(){
        return produtoRespository.consultarProdutosMaisVendios();
    }
}
