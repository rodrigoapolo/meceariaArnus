package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.Utils.FormatacaoCpfCnpj;
import com.arnus.merceariaarnus.dto.FornecedorDTO;
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
/*        verificarFornecedor(fornecedorDTO);

        FornecedorModel fornecedorModel = new FornecedorModel();
        criarFornecedor(fornecedorModel, fornecedorDTO);

        fornecedorRespository.save(fornecedorModel);*/
        return update(null, fornecedorDTO);
    }

    public FornecedorDTO update(Integer id, FornecedorDTO fornecedorDTO){
        FornecedorModel fornecedorModel = new FornecedorModel();
        if(id != null) {
            if (id == 0)
                throw new IllegalArgumentException("ID do fornecedor não pode ser 0");
            if (!fornecedorRespository.findById(id).isPresent())
                throw new IllegalArgumentException("ID do fornecedor não existe");

            fornecedorModel = fornecedorRespository.getReferenceById(id);
        }

        verificarFornecedor(fornecedorDTO);

        criarFornecedor(fornecedorModel, fornecedorDTO);

        fornecedorRespository.save(fornecedorModel);
        return fornecedorDTO;
    }

    private void verificarFornecedor(FornecedorDTO fornecedorDTO) {
        if(!categoriaFornecedorRespository.findById(fornecedorDTO.getCategoriaFornecedor()).isPresent())
            throw new IllegalArgumentException("ID da categoria fornecedor não existe");
    }

    private void criarFornecedor(FornecedorModel fornecedorModel, FornecedorDTO fornecedorDTO){
        fornecedorModel.setPessoaModel(criarPessoa(fornecedorDTO));
        fornecedorModel.setCnpj(FormatacaoCpfCnpj.formatarCpfCnpj(fornecedorDTO.getCnpj(), "CNPJ invalido"));
        fornecedorModel.setCategoriaFornecedor(categoriaFornecedorRespository
                .findById(fornecedorDTO.getCategoriaFornecedor()).get());
    }

    private PessoaModel criarPessoa(FornecedorDTO fornecedorDTO){
        PessoaModel pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(fornecedorDTO, pessoaModel);
        return pessoaModel;
    }
}
