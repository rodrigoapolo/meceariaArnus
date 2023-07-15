package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.CategoriaProdutoDTO;
import com.arnus.merceariaarnus.dto.FuncionarioDTO;
import com.arnus.merceariaarnus.model.CategoriaProdutoModel;
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
        PessoaModel pessoa = new PessoaModel();
        BeanUtils.copyProperties(funcionarioDTO, pessoa);

        FuncionarioModel funcionarioModel = new FuncionarioModel();
        funcionarioModel.setPessoaModel(pessoa);
        funcionarioModel.setClt(funcionarioDTO.getClt());

        funcionarioRespository.save(funcionarioModel);

        return funcionarioDTO;
    }
}
