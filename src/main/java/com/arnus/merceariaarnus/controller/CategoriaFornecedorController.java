package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.CategoriaFornecedorDTO;
import com.arnus.merceariaarnus.dto.CategoriaProdutoDTO;
import com.arnus.merceariaarnus.service.CategoriaFornecedorService;
import com.arnus.merceariaarnus.service.CategoriaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categoria-fornecedor")
public class CategoriaFornecedorController {

    @Autowired
    CategoriaFornecedorService categoriaFornecedorService;

    @PostMapping()
    public ResponseEntity<CategoriaFornecedorDTO> salvarCategoria(@RequestBody CategoriaFornecedorDTO categoria){
        CategoriaFornecedorDTO categoriaDTO = categoriaFornecedorService.save(categoria);
        return ResponseEntity.ok().body(categoriaDTO);
    }
}
