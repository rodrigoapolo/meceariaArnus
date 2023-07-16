package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.dto.ProdutoDTO;
import com.arnus.merceariaarnus.dto.view.ProdutosMiasVendidos;
import com.arnus.merceariaarnus.model.EmailModel;
import com.arnus.merceariaarnus.model.FornecedorModel;
import com.arnus.merceariaarnus.model.ProdutoModel;
import com.arnus.merceariaarnus.repository.ProdutoRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRespository produtoRespository;
    @Autowired
    CategoriaProdutoService categoriaProdutoService;
    @Autowired
    FornecedorService fornecedorService;
    @Autowired
    EmailService emailService;

    public ProdutoModel findById(Integer id){
        if (id == 0)
            throw new IllegalArgumentException("ID do funcionario não pode ser 0");

        Optional<ProdutoModel> produto =  produtoRespository.findByIdAndStatusTrue(id);
        if(!produto.isPresent())
            throw new IllegalArgumentException("ID do Produto não existe");

        return produto.get();
    }

    public ProdutoDTO salvar(ProdutoDTO produtoDTO){
        return update(null, produtoDTO);
    }

    public ProdutoDTO update(Integer id, ProdutoDTO produtoDTO){
        ProdutoModel produtoModel = new ProdutoModel();
        if(id != null) {
            produtoModel = findById(id);
        }

        produtoModel.setCategoriaProdutoModel(categoriaProdutoService.findById(produtoDTO.getCategoriaProduto()));
        produtoModel.setFornecedorModel(fornecedorService.findById(produtoDTO.getFornecedor()));
        BeanUtils.copyProperties(produtoDTO, produtoModel);

        produtoModel.setStatus(true);
        produtoRespository.save(produtoModel);

        return produtoDTO;
    }

    public void delete(Integer id){
        ProdutoModel produto = findById(id);
        produto.setStatus(false);
        produtoRespository.save(produto);
    }

    public Integer diminuirValorEstoque(Integer qtd, ProdutoModel produtoModel){
        if(produtoModel.getQtd() >= qtd){
            produtoModel.setQtd(produtoModel.getQtd() - qtd);
            produtoRespository.save(produtoModel);
        }else {
            throw new IllegalArgumentException("Estoque indisponivel para essa qualidade: "+qtd);
        }

        if(produtoModel.getQtd() < 300){
            EmailModel emailModel = new EmailModel();
            StringBuffer text = geralEmailProduto(produtoModel, emailModel);

            emailModel.setText(text.toString());
            emailService.sendEmail(emailModel);
        }

        return qtd;
    }

    private static StringBuffer geralEmailProduto(ProdutoModel produtoModel, EmailModel emailModel) {
        FornecedorModel fornecedorModel = produtoModel.getFornecedorModel();
        emailModel.setOwnerRef(fornecedorModel);
        emailModel.setEmailFrom("gogaragedev@gmail.com");
        emailModel.setEmailTo("rodrigoapolodev@gmail.com");
        emailModel.setSubject("Estoque em baixa");
        StringBuffer text = new StringBuffer();
        text.append(String.format("O estoque do produto %s está a baixo do nivel resta %d.%n", produtoModel.getNome(), produtoModel.getQtd()));
        text.append(String.format("Contato do Fornedor%n"));
        text.append(String.format("Fornedor: %s%n", fornecedorModel.getPessoaModel().getNome()));
        text.append(String.format("Email: %s%n", fornecedorModel.getPessoaModel().getEmail()));
        text.append(String.format("Telefone: %s%n", fornecedorModel.getPessoaModel().getTelefone()));
        text.append(String.format("Logradouro: %s", fornecedorModel.getPessoaModel().getEndereco().getLogradouro()));
        text.append(String.format(" %s, ", fornecedorModel.getPessoaModel().getEndereco().getNumero()));
        text.append(String.format("%s", fornecedorModel.getPessoaModel().getEndereco().getBairro()));
        text.append(String.format(" - %s.%n", fornecedorModel.getPessoaModel().getEndereco().getCidade()));
        text.append(String.format("Complemento: %s", fornecedorModel.getPessoaModel().getEndereco().getComplemento()));
        text.append(String.format("%nCEP: %s", fornecedorModel.getPessoaModel().getEndereco().getCep()));
        return text;
    }

    public List<ProdutosMiasVendidos> consultarProdutosMaisVendios(){
        return produtoRespository.consultarProdutosMaisVendios();
    }
}
