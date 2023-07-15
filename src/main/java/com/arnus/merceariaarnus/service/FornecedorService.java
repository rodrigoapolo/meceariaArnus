package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.Utils.FormatacaoCpfCnpj;
import com.arnus.merceariaarnus.dto.ClienteDTO;
import com.arnus.merceariaarnus.dto.FornecedorDTO;
import com.arnus.merceariaarnus.model.ClienteModel;
import com.arnus.merceariaarnus.model.FornecedorModel;
import com.arnus.merceariaarnus.model.PessoaModel;
import com.arnus.merceariaarnus.repository.CategoriaFornecedorRespository;
import com.arnus.merceariaarnus.repository.FornecedorRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

    @Autowired
    FornecedorRespository fornecedorRespository;
    @Autowired
    CategoriaFornecedorRespository categoriaFornecedorRespository;

    public FornecedorDTO salvar(FornecedorDTO fornecedorDTO){
        if(!categoriaFornecedorRespository.findById(fornecedorDTO.getCategoriaFornecedor()).isPresent())
            throw new IllegalArgumentException("ID da categoria fornecedor não existe");

        PessoaModel pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(fornecedorDTO, pessoaModel);
        fornecedorDTO.setCnpj(FormatacaoCpfCnpj.formatarCpfCnpj(fornecedorDTO.getCnpj(), "CNPJ invalido"));

        FornecedorModel fornecedorModel = new FornecedorModel();
        fornecedorModel.setPessoaModel(pessoaModel);
        fornecedorModel.setCnpj(fornecedorDTO.getCnpj());
        fornecedorModel.setCategoriaFornecedor(categoriaFornecedorRespository
                .findById(fornecedorDTO.getCategoriaFornecedor()).get());

        fornecedorRespository.save(fornecedorModel);
        return fornecedorDTO;
    }
}
