package com.walletapi.controller;

import com.walletapi.dtos.ItensDaVendaDto;
import com.walletapi.model.ItensDaVenda;
import com.walletapi.repository.ItensDaVendaRepository;
import com.walletapi.service.ItensDaVendaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<ItensDaVenda> list = itensDaVendaService.findAll();
        return ResponseEntity.ok(list.stream().map(
                e -> mapper.map(e, ItensDaVendaDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/buscarPorCliente")
    public ResponseEntity<List<ItensDaVendaDto>> ConsultarItensVdPorCliente(@RequestParam(name = "nome") String nome) {
        return ResponseEntity.ok(itensDaVendaService.litarItemDaVendaPorCliente(nome));
        /*    return ResponseEntity.ok(list.stream().map(
                rec -> mapper.map(rec , ItensDaVendaDto.class))
                    .collect(Collectors.toList()));*/
    }

    @GetMapping(value = "/buscarPorData")
    public ResponseEntity ConsultarItensVdPorData(@RequestParam(name = "data") String data) {
        return itensDaVendaService.litarItemDaVendaPorData(data)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/ItensVdEntreDatas")
    public ResponseEntity ConsultarItensVdEntreDatas(
            @RequestParam(name = "dtIni") String dtIni, @RequestParam(name = "dtFinal") String dtFinal) {
              List<ItensDaVendaDto> list = itensDaVendaService.ConsultarItensVdEntreDatas(dtIni, dtFinal);
        return ResponseEntity.ok(list.stream().map(
                e -> mapper.map(e, ItensDaVendaDto.class))
                .map(r -> ResponseEntity.ok().body(r))
              //  .orElse(ResponseEntity.notFound().build()));
          .collect(Collectors.toList()));
    }


}
