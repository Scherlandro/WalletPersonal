package com.walletapi.controller;

import com.walletapi.dtos.ItensDaVendaDto;
import com.walletapi.model.ItensDaVenda;
import com.walletapi.service.ItensDaVendaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/itensDaVenda")
public class ItensVendaController {


    final ItensDaVendaService itensDaVendaService;
    @Autowired
    private ModelMapper mapper;

    public ItensVendaController(ItensDaVendaService itensDaVendaService){
        this.itensDaVendaService = itensDaVendaService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ItensDaVendaDto>> listarItensDaVenda() {
       List<ItensDaVenda> list = itensDaVendaService.findAll();
        return ResponseEntity.ok(list.stream().map(
                e -> mapper.map(e, ItensDaVendaDto.class))
                .collect(Collectors.toList()));

    }

    @GetMapping(value = "/buscarPorData")
    public ResponseEntity ConsultarItensVdPorData(@RequestParam(name = "data") String data) {
        return itensDaVendaService.litarItemDaVendaPorData(data)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping(value = "/ItensVdEntreDatas")
    public ResponseEntity ConsultarItensVdEntreDatas(
            @RequestParam(name = "dtIni") String dtIni, @RequestParam(name = "dtFinal")String dtFinal) {
             return ResponseEntity.ok(itensDaVendaService.ConsultarItensVdEntreDatas(dtIni,dtFinal));


    }

}
