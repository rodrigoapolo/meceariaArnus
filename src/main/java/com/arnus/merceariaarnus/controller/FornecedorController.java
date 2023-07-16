package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.FornecedorDTO;
import com.arnus.merceariaarnus.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/fornecedor")
public class FornecedorController {

    @Autowired
    FornecedorService fornecedorService;

    @PostMapping()
    public ResponseEntity<FornecedorDTO> salvar(@RequestBody FornecedorDTO fornecedor){
        FornecedorDTO fornecedorDTO = fornecedorService.salvar(fornecedor);
        return ResponseEntity.ok().body(fornecedorDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<FornecedorDTO> update(@PathVariable Integer id, @RequestBody FornecedorDTO fornecedor){
        FornecedorDTO fornecedorDTO = fornecedorService.update(id, fornecedor);
        return ResponseEntity.ok().body(fornecedorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        fornecedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
