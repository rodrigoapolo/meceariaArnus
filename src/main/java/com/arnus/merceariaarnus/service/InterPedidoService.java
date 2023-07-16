package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.InterPedidoDTO;
import com.arnus.merceariaarnus.model.InterPedidoModel;
import com.arnus.merceariaarnus.model.ProdutoModel;
import com.arnus.merceariaarnus.repository.InterPedidoRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterPedidoService {

    @Autowired
    InterPedidoRespository interPedidoRespository;

    @Autowired
    ProdutoService produtoService;

    public InterPedidoModel geralInter(InterPedidoDTO interPedidoDTO){
        InterPedidoModel interPedidoModel = new InterPedidoModel();

        ProdutoModel produtoModel = produtoService.findById(interPedidoDTO.getProduto());
        interPedidoModel.setProdutoModel(produtoModel);

        interPedidoModel.setQtd(interPedidoDTO.getQtd());
        interPedidoModel.setValorUnidade(produtoModel.getPreco());
        Double subTotal =  interPedidoDTO.getQtd() * produtoModel.getPreco();
        interPedidoModel.setSubTotal(subTotal);

        return interPedidoModel;
    }
}
