package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.InterPedidoDTO;
import com.arnus.merceariaarnus.dto.PedidoDTO;
import com.arnus.merceariaarnus.model.ClienteModel;
import com.arnus.merceariaarnus.model.FuncionarioModel;
import com.arnus.merceariaarnus.model.InterPedidoModel;
import com.arnus.merceariaarnus.model.PedidoModel;
import com.arnus.merceariaarnus.repository.PedidoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PedidoService {

    @Autowired
    PedidoRespository pedidoRespository;

    @Autowired
    ClienteService clienteService;
    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    InterPedidoService interPedidoService;


    public PedidoDTO salvar(PedidoDTO pedidoDTO) {
        PedidoModel pedidoModel = new PedidoModel();

        ClienteModel cliente = clienteService.findById(pedidoDTO.getCliente());
        FuncionarioModel funcionario = funcionarioService.findById(pedidoDTO.getFuncionario());

        Double valorTotal = 0.0;
        for (InterPedidoDTO inter : pedidoDTO.getInterPedidoDTO()) {
            InterPedidoModel interPedidoModel = interPedidoService.geralInter(inter);
            pedidoModel.getIntensPedidos().add(interPedidoModel);
            valorTotal += interPedidoModel.getSubTotal();
        }

        pedidoModel.setTotal(valorTotal);
        pedidoModel.setData(LocalDate.now());

        pedidoModel.setClienteModel(cliente);
        pedidoModel.setFuncionarioModel(funcionario);

        pedidoRespository.save(pedidoModel);
        return pedidoDTO;
    }
}
