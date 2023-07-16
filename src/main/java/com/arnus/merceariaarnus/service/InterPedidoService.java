package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.InterPedidoDTO;
import com.arnus.merceariaarnus.model.InterPedidoModel;
import com.arnus.merceariaarnus.model.PedidoModel;
import com.arnus.merceariaarnus.model.ProdutoModel;
import com.arnus.merceariaarnus.repository.InterPedidoRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class InterPedidoService {

    @Autowired
    InterPedidoRespository interPedidoRespository;

    @Autowired
    ProdutoService produtoService;

    public void geralInter(PedidoModel pedidoModel, List<InterPedidoDTO> intensPedidosDTO){
        List <InterPedidoModel> intensPedidosModel = new LinkedList<>();

        Double valorTotal = 0.0;
        for (InterPedidoDTO interPedidoDTO : intensPedidosDTO) {
            ProdutoModel produtoModel = produtoService.findById(interPedidoDTO.getProduto());
            InterPedidoModel interModel = new InterPedidoModel();

            interModel.setProdutoModel(produtoModel);
            interModel.setValorUnidade(produtoModel.getPreco());
            Integer qtd = produtoService.diminuirValorEstoque(interPedidoDTO.getQtd(), produtoModel);
            interModel.setQtd(qtd);
            Double subTotal =  interPedidoDTO.getQtd() * produtoModel.getPreco();
            interModel.setSubTotal(subTotal);
            valorTotal += interModel.getSubTotal();
            interModel.setPedidoModel(pedidoModel);
            intensPedidosModel.add(interModel);
        }

        pedidoModel.setTotal(valorTotal);

        interPedidoRespository.saveAll(intensPedidosModel);
    }
}
