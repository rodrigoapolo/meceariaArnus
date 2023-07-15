package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.ClienteDTO;
import com.arnus.merceariaarnus.dto.FuncionarioDTO;
import com.arnus.merceariaarnus.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping()
    public ResponseEntity<FuncionarioDTO> salvarFuncionario(@RequestBody FuncionarioDTO funcionario){
        FuncionarioDTO funcionarioDTO = funcionarioService.salvar(funcionario);
        return ResponseEntity.ok().body(funcionarioDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable Integer id, @RequestBody FuncionarioDTO funcionario){
        FuncionarioDTO funcionarioDTO = funcionarioService.update(id, funcionario);
        return ResponseEntity.ok().body(funcionarioDTO);
    }

}
