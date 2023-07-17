package com.arnus.merceariaarnus.controller;

import com.arnus.merceariaarnus.dto.PedidoDTO;
import com.arnus.merceariaarnus.dto.view.PedidoPorData;
import com.arnus.merceariaarnus.dto.view.TotalPedido;
import com.arnus.merceariaarnus.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping()
    public ResponseEntity<PedidoDTO> salvar(@RequestBody PedidoDTO pedido){
        PedidoDTO pedidoDTO = pedidoService.salvar(pedido);
        return ResponseEntity.status(201).body(pedidoDTO);
    }

    @GetMapping("/total-pedido")
    public ResponseEntity<TotalPedido> consultarTotalPedido(){
        TotalPedido total = pedidoService.consultarTotalPedido();
        return ResponseEntity.ok().body(total);
    }

    @GetMapping("/pedidos-por-periodo/{inicio}/{fim}")
    public ResponseEntity<List<PedidoPorData>> pedidosPorPeriodo(
            @PathVariable("inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio,
            @PathVariable("fim") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fim){
        List<PedidoPorData> pedidosPorData = pedidoService.consultarPedidosPorPeriodo(inicio, fim);
        return ResponseEntity.ok().body(pedidosPorData);
    }
}
