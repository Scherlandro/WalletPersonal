package com.walletapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walletapi.dtos.ItensDaVendaDto;
import com.walletapi.service.ItensDaVendaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/itensDaVenda")
public class ItensVendaController {


    @Autowired
    private ItensDaVendaService itensDaVendaService;
    @Autowired
    private ModelMapper mapper;


    @GetMapping(path = "/all")
    public ResponseEntity<List<ItensDaVendaDto>> listarItensDaVenda() {
        List<ItensDaVendaDto> list = itensDaVendaService.findAll();
        return ResponseEntity.ok(list.stream().map(
                e -> mapper.map(e, ItensDaVendaDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/buscarPorCliente")
    public ResponseEntity<List<ItensDaVendaDto>> ConsultarItensVdPorCliente(@RequestParam(name = "nome") String nome) {
        List<ItensDaVendaDto> list = itensDaVendaService.litarItemDaVendaPorCliente(nome);
        return ResponseEntity.ok(list.stream().map(
                e -> mapper.map(e, ItensDaVendaDto.class))
                .collect(Collectors.toList()));
    }

       @GetMapping(value = "/buscarPorIdVd")
    public ResponseEntity<List<ItensDaVendaDto>> ConsultarItensVdPorIdVd(@RequestParam(value = "id") Integer id) {
        List<ItensDaVendaDto> list = itensDaVendaService.listarItensDaVdPorId(id);
        return ResponseEntity.ok(list.stream().map(
                e -> mapper.map(e, ItensDaVendaDto.class))
                 .collect(Collectors.toList()));
    }

    @GetMapping(value = "/buscarPorData")
    public ResponseEntity<List<ItensDaVendaDto>> ConsultarItensVdPorData(@RequestParam(name = "data") String data) {
        List<ItensDaVendaDto> list = itensDaVendaService.litarItemDaVendaPorData(data);
        return ResponseEntity.ok(list.stream().map(
                e -> mapper.map(e, ItensDaVendaDto.class))
                 .collect(Collectors.toList()));
    }

    @GetMapping(value = "/ItensVdEntreDatas")
    public ResponseEntity<List<ItensDaVendaDto>> ConsultarItensVdEntreDatas(
            @RequestParam(name = "dtIni") String dtIni, @RequestParam(name = "dtFinal") String dtFinal) {
              List<ItensDaVendaDto> list = itensDaVendaService.ConsultarItensVdEntreDatas(dtIni, dtFinal);
        return ResponseEntity.ok(list.stream().map(
                e -> mapper.map(e, ItensDaVendaDto.class))
                .collect(Collectors.toList()));
    }


}
