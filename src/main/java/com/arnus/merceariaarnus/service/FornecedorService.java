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

import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    FornecedorRespository fornecedorRespository;
    @Autowired
    CategoriaFornecedorRespository categoriaFornecedorRespository;

    public FornecedorModel findById(Integer id){
        if (id == 0)
            throw new IllegalArgumentException("ID do fornecedor não pode ser 0");

        Optional<FornecedorModel> fornecedor = fornecedorRespository.findByIdAndStatusTrue(id);
        if (!fornecedor.isPresent())
            throw new IllegalArgumentException("ID do fornecedor não existe");

        return fornecedor.get();
    }

    public FornecedorDTO salvar(FornecedorDTO fornecedorDTO){
        return update(null, fornecedorDTO);
    }

    public FornecedorDTO update(Integer id, FornecedorDTO fornecedorDTO){
        FornecedorModel fornecedorModel = new FornecedorModel();
        if(id != null) {
            fornecedorModel = findById(id);
        }

        verificarIdCategoriaFornecedor(fornecedorDTO);

        criarFornecedor(fornecedorModel, fornecedorDTO);
        fornecedorModel.setStatus(true);
        fornecedorRespository.save(fornecedorModel);
        return fornecedorDTO;
    }

    private void verificarIdCategoriaFornecedor(FornecedorDTO fornecedorDTO) {
        if(!categoriaFornecedorRespository.findByIdAndStatusTrue(fornecedorDTO.getCategoriaFornecedor()).isPresent())
            throw new IllegalArgumentException("ID da categoria fornecedor não existe");
    }

    private void criarFornecedor(FornecedorModel fornecedorModel, FornecedorDTO fornecedorDTO){
        fornecedorModel.setPessoaModel(criarPessoa(fornecedorDTO));
        fornecedorModel.setCnpj(FormatacaoCpfCnpj.formatarCpfCnpj(fornecedorDTO.getCnpj(), "CNPJ invalido"));
        fornecedorModel.setCategoriaFornecedor(categoriaFornecedorRespository
                .findByIdAndStatusTrue(fornecedorDTO.getCategoriaFornecedor()).get());
    }

    private PessoaModel criarPessoa(FornecedorDTO fornecedorDTO){
        PessoaModel pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(fornecedorDTO, pessoaModel);
        return pessoaModel;
    }

    public void delete(Integer id){
        FornecedorModel fornecedor = findById(id);
        fornecedor.setStatus(false);
        fornecedorRespository.save(fornecedor);
    }
}
