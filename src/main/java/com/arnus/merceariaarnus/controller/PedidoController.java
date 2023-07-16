package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.PedidoDTO;
import com.arnus.merceariaarnus.dto.ProdutoDTO;
import com.arnus.merceariaarnus.dto.view.TotalVenda;
import com.arnus.merceariaarnus.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping()
    public ResponseEntity<PedidoDTO> salvar(@RequestBody PedidoDTO pedido){
        PedidoDTO pedidoDTO = pedidoService.salvar(pedido);
        return ResponseEntity.ok().body(pedidoDTO);
    }

    @GetMapping("/total-vendas")
    public ResponseEntity<TotalVenda> totalVenda(){
        TotalVenda total = pedidoService.totalVendas();
        return ResponseEntity.ok().body(total);
    }
}
