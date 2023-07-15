package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.CategoriaFornecedorDTO;
import com.arnus.merceariaarnus.service.CategoriaFornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{id}")
    public ResponseEntity<CategoriaFornecedorDTO> update(@PathVariable Integer id, @RequestBody CategoriaFornecedorDTO categoria){
        CategoriaFornecedorDTO categoriaDTO = categoriaFornecedorService.update(id, categoria);
        return ResponseEntity.ok().body(categoriaDTO);
    }
}
