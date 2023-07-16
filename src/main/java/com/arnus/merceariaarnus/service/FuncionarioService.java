package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.Utils.FormatacaoCpfCnpj;
import com.arnus.merceariaarnus.dto.FuncionarioDTO;
import com.arnus.merceariaarnus.model.FuncionarioModel;
import com.arnus.merceariaarnus.model.PessoaModel;
import com.arnus.merceariaarnus.repository.FuncionarioRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRespository funcionarioRespository;

    public FuncionarioModel findById(Integer id){
        if (id == 0)
            throw new IllegalArgumentException("ID do funcionario não pode ser 0");

        Optional <FuncionarioModel> funcionario = funcionarioRespository.findByIdAndStatusTrue(id);
        if (!funcionarioRespository.findByIdAndStatusTrue(id).isPresent())
            throw new IllegalArgumentException("ID do funcionario não existe");

        return funcionario.get();
    }

    public FuncionarioDTO salvar(FuncionarioDTO funcionarioDTO){
        return update(null, funcionarioDTO);
    }

    public FuncionarioDTO update(Integer id, FuncionarioDTO funcionarioDTO){
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        if(id != null) {
            funcionarioModel = findById(id);
        }

        criarFuncionario(funcionarioModel, funcionarioDTO);
        funcionarioModel.setStatus(true);
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

    public void delete(Integer id){
        FuncionarioModel funcionario = findById(id);
        funcionario.setStatus(false);
        funcionarioRespository.save(funcionario);
    }
}
