package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.Utils.FormatacaoCpfCnpj;
import com.arnus.merceariaarnus.dto.FuncionarioDTO;
import com.arnus.merceariaarnus.model.FornecedorModel;
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
            verificarFuncionario(id);

            funcionarioModel = funcionarioRespository.findByIdAndStatusTrue(id).get();
        }

        criarFuncionario(funcionarioModel, funcionarioDTO);
        funcionarioModel.setStatus(true);
        funcionarioRespository.save(funcionarioModel);
        return funcionarioDTO;
    }

    private void verificarFuncionario(Integer id) {
        if (id == 0)
            throw new IllegalArgumentException("ID do funcionario não pode ser 0");
        if (!funcionarioRespository.findByIdAndStatusTrue(id).isPresent())
            throw new IllegalArgumentException("ID do funcionario não existe");
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
        verificarFuncionario(id);
        FuncionarioModel funcionario = funcionarioRespository.findByIdAndStatusTrue(id).get();
        funcionario.setStatus(false);
        funcionarioRespository.save(funcionario);
    }
}
