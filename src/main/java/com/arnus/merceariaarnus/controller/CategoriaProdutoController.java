package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.CategoriaProdutoDTO;
import com.arnus.merceariaarnus.dto.ProdutoDTO;
import com.arnus.merceariaarnus.service.CategoriaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/categoria-produto")
public class CategoriaProdutoController {

    @Autowired
    CategoriaProdutoService categoriaProdutoService;

/*    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProdutoDTO> getCategoriaId(@PathVariable Integer id){
        CategoriaProdutoDTO categoriaDTO = categoriaProdutoService.getCategoriaId(id);
        return ResponseEntity.ok().body(categoriaDTO);
    }*/

    @PostMapping()
    public ResponseEntity<CategoriaProdutoDTO> salvar(@RequestBody CategoriaProdutoDTO categoria){
        CategoriaProdutoDTO categoriaDTO = categoriaProdutoService.salvar(categoria);
        return ResponseEntity.ok().body(categoriaDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<CategoriaProdutoDTO> update(@PathVariable Integer id, @RequestBody CategoriaProdutoDTO categoria){
        CategoriaProdutoDTO categoriaDTO = categoriaProdutoService.update(id, categoria);
        return ResponseEntity.ok().body(categoriaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoriaProdutoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
