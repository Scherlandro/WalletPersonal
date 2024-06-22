package com.walletapi.controller;

import com.walletapi.dtos.VendasDto;
import com.walletapi.model.Vendas;
import com.walletapi.service.VendasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/vendas")
public class VendasController {
    @Autowired
    private VendasService vendas_serv;
    private VendasDto vendasDto;
    @Autowired
    private ModelMapper mapper;

    @GetMapping(path = "/all")
    public ResponseEntity<List<VendasDto>> listarVendas() {
        List<Vendas> list = vendas_serv.listarVendas();
        return ResponseEntity.ok(list.stream().map(
                e -> mapper.map(e, VendasDto.class)).collect(Collectors.toList()));
    }

   @GetMapping(path = "/{idVenda}")
    public ResponseEntity consultarPorCod(@PathVariable("idVenda") Integer idVenda) {
        Optional<Vendas> vendas = vendas_serv.litarVendaPorCod(idVenda);
        return ResponseEntity.ok(vendas.map(
                e -> mapper.map(e,VendasDto.class)).map(r->ResponseEntity.ok().body(r))
                .orElse(ResponseEntity.notFound().build()));
    }

   @GetMapping(path = "/buscarVdPorCliente")
    public ResponseEntity listarVendasPorCliente(@RequestParam(name ="nomeCliente") String nomeCliente) {
        Optional<Vendas> vendas = vendas_serv.litarVendaPorCliente(nomeCliente);
      // return ResponseEntity.ok(vendas.stream().map(
        return ResponseEntity.ok(vendas.map(
                e -> mapper.map(e,VendasDto.class))
               // .collect(Collectors.toList()));
                .map(r->ResponseEntity.ok().body(r))
                .orElse(ResponseEntity.notFound().build()));
    }


    @PostMapping(path = "/salvar")
    public ResponseEntity salvar(@RequestBody VendasDto vendasDto) {
        vendas_serv.save(mapper.map(vendasDto, Vendas.class));
        Optional<Vendas> vendas = vendas_serv.findById(vendasDto.getIdVenda());
        return ResponseEntity.ok(vendas.map(e -> mapper.map(e,
                VendasDto.class)).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build()));
    }

    @PutMapping(path = "/editar")
    public ResponseEntity editar(@RequestBody VendasDto vendasDto) {
        vendas_serv.save(mapper.map(vendasDto, Vendas.class));
        Optional<Vendas> vendas = vendas_serv.findById(vendasDto.getIdVenda());
        return ResponseEntity.ok(vendas.map(e -> mapper.map(e,
                VendasDto.class)).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build()));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Integer id) {
        vendas_serv.delete(id);
        return ResponseEntity.noContent().build();
    }


}