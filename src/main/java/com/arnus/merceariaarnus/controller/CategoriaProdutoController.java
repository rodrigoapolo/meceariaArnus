package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.CategoriaProdutoDTO;
import com.arnus.merceariaarnus.service.CategoriaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categoria-produto")
public class CategoriaProdutoController {

    @Autowired
    CategoriaProdutoService categoriaProdutoService;

    @PostMapping()
    public ResponseEntity<CategoriaProdutoDTO> salvarCategoria(@RequestBody CategoriaProdutoDTO categoria){
        CategoriaProdutoDTO categoriaDTO = categoriaProdutoService.save(categoria);
        return ResponseEntity.ok().body(categoriaDTO);
    }
}
