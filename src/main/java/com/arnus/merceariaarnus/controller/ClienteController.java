package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.ClienteDTO;
import com.arnus.merceariaarnus.dto.view.ClienteMaisCompra;
import com.arnus.merceariaarnus.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping()
    public ResponseEntity<ClienteDTO> salvar(@RequestBody ClienteDTO cliente){
        ClienteDTO clienteDTO = clienteService.salvar(cliente);
        return ResponseEntity.status(201).body(clienteDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody ClienteDTO cliente){
        ClienteDTO clienteDTO = clienteService.update(id, cliente);
        return ResponseEntity.status(201).body(clienteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clientes-mais-compram")
    public ResponseEntity<List<ClienteMaisCompra>> consultarClientesMaisCompram(){
        List<ClienteMaisCompra> clienteMaisCompraram = clienteService.consultarClientesMaisCompram();
        return ResponseEntity.ok().body(clienteMaisCompraram);
    }
}
