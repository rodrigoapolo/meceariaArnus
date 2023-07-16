package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.Utils.FormatacaoCpfCnpj;
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
        return update(null, fornecedorDTO);
    }

    public FornecedorDTO update(Integer id, FornecedorDTO fornecedorDTO){
        FornecedorModel fornecedorModel = new FornecedorModel();
        if(id != null) {
            verificarFornecedor(id);

            fornecedorModel = fornecedorRespository.findByIdAndStatusTrue(id).get();
        }

        verificarIdCategoriaFornecedor(fornecedorDTO);

        criarFornecedor(fornecedorModel, fornecedorDTO);
        fornecedorModel.setStatus(true);
        fornecedorRespository.save(fornecedorModel);
        return fornecedorDTO;
    }

    private void verificarFornecedor(Integer id) {
        if (id == 0)
            throw new IllegalArgumentException("ID do fornecedor não pode ser 0");
        if (!fornecedorRespository.findByIdAndStatusTrue(id).isPresent())
            throw new IllegalArgumentException("ID do fornecedor não existe");
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
        verificarFornecedor(id);
        FornecedorModel fornecedor = fornecedorRespository.findByIdAndStatusTrue(id).get();
        fornecedor.setStatus(false);
        fornecedorRespository.save(fornecedor);
    }
}
