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

        pedidoModel.setClienteModel(cliente);
        pedidoModel.setFuncionarioModel(funcionario);
        pedidoModel.setData(LocalDate.now());

        pedidoModel = pedidoRespository.save(pedidoModel);

        interPedidoService.geralInter(pedidoModel, pedidoDTO.getInterPedidoDTO());

        pedidoRespository.save(pedidoModel);

        return pedidoDTO;
    }
}
