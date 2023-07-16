package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.Utils.FormatacaoCpfCnpj;
import com.arnus.merceariaarnus.dto.CategoriaProdutoDTO;
import com.arnus.merceariaarnus.dto.ClienteDTO;
import com.arnus.merceariaarnus.model.CategoriaProdutoModel;
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

    public ClienteDTO salvar(ClienteDTO clienteDTO){
        return update(null,clienteDTO);
    }

    public ClienteDTO update(Integer id, ClienteDTO clienteDTO){
        ClienteModel clienteModel = new ClienteModel();
        if(id != null){
            verificarCliente(id);

            clienteModel = clienteRespository.findByIdAndStatusTrue(id).get();
        }

        criarCliente(clienteModel, clienteDTO);
        clienteModel.setStatus(true);
        clienteRespository.save(clienteModel);
        return clienteDTO;
    }

    private void verificarCliente(Integer id) {
        if(id == 0)
            throw new IllegalArgumentException("ID do cliente não pode ser 0");
        if(!clienteRespository.findByIdAndStatusTrue(id).isPresent())
            throw new IllegalArgumentException("ID do cliente não existe");
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
        verificarCliente(id);
        ClienteModel cliente = clienteRespository.findByIdAndStatusTrue(id).get();
        cliente.setStatus(false);
        clienteRespository.save(cliente);
    }
}
