package com.walletapi.service;

import java.util.List;
import java.util.Optional;

import com.walletapi.dtos.VendasDto;
import com.walletapi.model.Vendas;

public interface VendasService {

    List<Vendas> listarVendas();
    Vendas save(Vendas vendas) ;
    Optional<Vendas> litarVendaPorCod(Integer id) ;
    List<Vendas> litarVendaPorCliente(String name) ;
    Optional<Vendas> findById(Integer id) ;
    void delete(Integer id);

}