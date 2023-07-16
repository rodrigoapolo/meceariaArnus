package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.Utils.FormatacaoCpfCnpj;
import com.arnus.merceariaarnus.dto.ClienteDTO;
import com.arnus.merceariaarnus.dto.view.ClienteMaisCompra;
import com.arnus.merceariaarnus.model.ClienteModel;
import com.arnus.merceariaarnus.model.PessoaModel;
import com.arnus.merceariaarnus.repository.ClienteRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRespository clienteRespository;

    public ClienteModel findById(Integer id){
        if(id == 0)
            throw new IllegalArgumentException("ID do cliente não pode ser 0");

        Optional<ClienteModel> cliente = clienteRespository.findByIdAndStatusTrue(id);
        if(!cliente.isPresent())
            throw new IllegalArgumentException("ID do cliente não existe");

        return cliente.get();
    }

    public ClienteDTO salvar(ClienteDTO clienteDTO){
        return update(null,clienteDTO);
    }

    public ClienteDTO update(Integer id, ClienteDTO clienteDTO){
        ClienteModel clienteModel = new ClienteModel();
        if(id != null){
            clienteModel = findById(id);
        }

        criarCliente(clienteModel, clienteDTO);
        clienteModel.setStatus(true);
        clienteRespository.save(clienteModel);
        return clienteDTO;
    }

    private void criarCliente(ClienteModel clienteModel, ClienteDTO clienteDTO){
        clienteModel.setPessoaModel(criarPessoa(clienteDTO));
        clienteModel.setCpf(FormatacaoCpfCnpj.formatarCpfCnpj(clienteDTO.getCpf(), "CPF invalido"));
    }

    private PessoaModel criarPessoa(ClienteDTO clienteDTO){
        PessoaModel pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(clienteDTO, pessoaModel);
        return pessoaModel;
    }

    public void delete(Integer id){
        ClienteModel cliente = findById(id);
        cliente.setStatus(false);
        clienteRespository.save(cliente);
    }

    public List<ClienteMaisCompra> consultarClientesMaisCompram(){
        return clienteRespository.consultarClientesMaisCompram();
    }
}
