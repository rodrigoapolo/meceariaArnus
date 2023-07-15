package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.ClienteDTO;
import com.arnus.merceariaarnus.dto.FornecedorDTO;
import com.arnus.merceariaarnus.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fornecedor")
public class FornecedorController {

    @Autowired
    FornecedorService fornecedorService;

    @PostMapping()
    public ResponseEntity<FornecedorDTO> salvarCliente(@RequestBody FornecedorDTO fornecedor){
        FornecedorDTO fornecedorDTO = fornecedorService.salvar(fornecedor);
        return ResponseEntity.ok().body(fornecedorDTO);
    }

}
