package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.ProdutoDTO;
import com.arnus.merceariaarnus.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping()
    public ResponseEntity<ProdutoDTO> salvarProduto(@RequestBody ProdutoDTO produto){
        ProdutoDTO produtoDTO = produtoService.salvar(produto);
        return ResponseEntity.ok().body(produtoDTO);
    }

}
