package com.walletapi.controller;

import com.walletapi.model.Pais;
import com.walletapi.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisRepository repository;
    private String nome_pais;


    @GetMapping(path = "/all")
    public ResponseEntity<List<Pais>>listarPaises(){
        return ResponseEntity.ok(repository.findAll());
    }


        @GetMapping(path = "/{id_paises}")
        public ResponseEntity consultarPaisPorId(@PathVariable("id_paises") Integer id_paises){
            return repository.findById(id_paises).map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());
        }


    @GetMapping(value = "/buscarPorNomeDoPais")
    public ResponseEntity consultarPaisPorNome(@RequestParam(name ="nome_pais") String nome_pais){
        return Arrays.stream(repository.busarPorNome_pais(nome_pais).toArray()).findFirst().map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

}


