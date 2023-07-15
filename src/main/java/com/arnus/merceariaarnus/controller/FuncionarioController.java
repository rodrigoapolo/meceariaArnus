package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.ClienteDTO;
import com.arnus.merceariaarnus.dto.FuncionarioDTO;
import com.arnus.merceariaarnus.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
