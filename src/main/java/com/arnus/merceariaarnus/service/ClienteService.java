package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.ClienteDTO;
import com.arnus.merceariaarnus.model.ClienteModel;
import com.arnus.merceariaarnus.model.PessoaModel;
import com.arnus.merceariaarnus.repository.ClienteRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    ClienteRespository clienteRespository;

    public ClienteDTO save(ClienteDTO clienteDTO){
        PessoaModel pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(clienteDTO, pessoaModel);
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setPessoaModel(pessoaModel);
        clienteModel.setCpf(clienteDTO.getCpf());
        clienteRespository.save(clienteModel);
        return clienteDTO;
    }
}
