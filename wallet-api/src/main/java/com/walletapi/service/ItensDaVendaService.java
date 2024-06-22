package com.walletapi.service;

import com.walletapi.dtos.ItensDaVendaDto;
import com.walletapi.model.ItensDaVenda;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface ItensDaVendaService {

    List<ItensDaVenda> findAll();

    Optional<ItensDaVenda> findById(Integer id);

    List<ItensDaVendaDto> ConsultarItensVdEntreDatas(String dtIni, String dtFinal);

     Optional<ItensDaVenda> litarItemDaVendaPorData(String dt);

     List<ItensDaVendaDto> litarItemDaVendaPorCliente(String nome);

    ItensDaVenda save(ItensDaVenda itensDaVenda);
}
