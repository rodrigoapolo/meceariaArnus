package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.CategoriaProdutoDTO;
import com.arnus.merceariaarnus.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/categoria-produto")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

/*
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProdutoDTO> getCategoriaId(@PathVariable Integer id){
        CategoriaProdutoDTO categoriaDTO = categoriaService.findById(id);
        return ResponseEntity.ok().body(categoriaDTO);
    }
*/

    @PostMapping()
    public ResponseEntity<CategoriaProdutoDTO> salvar(@RequestBody CategoriaProdutoDTO categoria){
        CategoriaProdutoDTO categoriaDTO = categoriaService.salvar(categoria);
        return ResponseEntity.ok().body(categoriaDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<CategoriaProdutoDTO> update(@PathVariable Integer id, @RequestBody CategoriaProdutoDTO categoria){
        CategoriaProdutoDTO categoriaDTO = categoriaService.update(id, categoria);
        return ResponseEntity.ok().body(categoriaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
