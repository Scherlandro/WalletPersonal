package com.walletapi.service;

import com.walletapi.dtos.VendasDto;
import com.walletapi.model.Vendas;

import java.util.List;
import java.util.Optional;

public interface VendasService {

    List<Vendas> listarVendas();
   Vendas save(Vendas vendas) ;
    Optional<Vendas> litarVendaPorCod(Integer id) ;
    Optional<Vendas> litarVendaPorCliente(String name) ;
    Optional<Vendas> findById(Integer id) ;
    void delete(Integer id);

}