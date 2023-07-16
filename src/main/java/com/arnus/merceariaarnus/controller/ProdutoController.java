package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.ProdutoDTO;
import com.arnus.merceariaarnus.dto.view.ProdutosMiasVendidos;
import com.arnus.merceariaarnus.dto.view.TotalPedido;
import com.arnus.merceariaarnus.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping()
    public ResponseEntity<ProdutoDTO> salvar(@RequestBody ProdutoDTO produto){
        ProdutoDTO produtoDTO = produtoService.salvar(produto);
        return ResponseEntity.ok().body(produtoDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Integer id, @RequestBody ProdutoDTO produto){
        ProdutoDTO produtoDTO = produtoService.update(id, produto);
        return ResponseEntity.ok().body(produtoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/produtos-mais-vendios")
    public ResponseEntity<List<ProdutosMiasVendidos>> consultarProdutosMaisVendios(){
        List<ProdutosMiasVendidos> produtosMiasVendidos = produtoService.consultarProdutosMaisVendios();
        return ResponseEntity.ok().body(produtosMiasVendidos);
    }
}
