package com.walletapi.service;

import java.util.List;
import java.util.Optional;

import com.walletapi.dtos.ItensDaVendaDto;
import com.walletapi.model.ItensDaVenda;


public interface ItensDaVendaService {

    List<ItensDaVendaDto> findAll();

    Optional<ItensDaVenda> findById(Integer id);

    List<ItensDaVendaDto> ConsultarItensVdEntreDatas(String dtIni, String dtFinal);

     List<ItensDaVendaDto> litarItemDaVendaPorData(String dt);

     List<ItensDaVendaDto> litarItemDaVendaPorCliente(String nome);

    ItensDaVenda save(ItensDaVenda itensDaVenda);
}
