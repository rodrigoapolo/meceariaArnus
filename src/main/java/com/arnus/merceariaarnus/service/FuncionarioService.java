package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.Utils.FormatacaoCpfCnpj;
import com.arnus.merceariaarnus.dto.ClienteDTO;
import com.arnus.merceariaarnus.dto.FuncionarioDTO;
import com.arnus.merceariaarnus.model.ClienteModel;
import com.arnus.merceariaarnus.model.FuncionarioModel;
import com.arnus.merceariaarnus.model.PessoaModel;
import com.arnus.merceariaarnus.repository.FuncionarioRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRespository funcionarioRespository;

    public FuncionarioDTO salvar(FuncionarioDTO funcionarioDTO){
        return update(null, funcionarioDTO);
    }

    public FuncionarioDTO update(Integer id, FuncionarioDTO funcionarioDTO){
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        if(id != null) {
            if (id == 0)
                throw new IllegalArgumentException("ID do funcionario não pode ser 0");
            if (!funcionarioRespository.findById(id).isPresent())
                throw new IllegalArgumentException("ID do funcionario não existe");

            funcionarioModel = funcionarioRespository.getReferenceById(id);
        }

        criarFuncionario(funcionarioModel, funcionarioDTO);

        funcionarioRespository.save(funcionarioModel);
        return funcionarioDTO;
    }

    private void criarFuncionario(FuncionarioModel funcionarioModel, FuncionarioDTO funcionarioDTO){
        funcionarioModel.setPessoaModel(criarPessoa(funcionarioDTO));
        funcionarioModel.setClt(FormatacaoCpfCnpj.formatarCpfCnpj(funcionarioDTO.getClt(), "CLT invalido"));
    }

    private PessoaModel criarPessoa(FuncionarioDTO funcionarioDTO){
        PessoaModel pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(funcionarioDTO, pessoaModel);
        return pessoaModel;
    }
}
