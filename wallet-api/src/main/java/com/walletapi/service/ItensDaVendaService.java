package com.walletapi.service;

import com.walletapi.dtos.ItensDaVendaDto;
import com.walletapi.model.ItensDaVenda;

import java.util.List;
import java.util.Optional;


public interface ItensDaVendaService {

    List<ItensDaVenda> findAll();

    Optional<ItensDaVenda> findById(Integer id);

     List<ItensDaVendaDto> ConsultarItensVdEntreDatas(String dtIni, String dtFinal);

     Optional<ItensDaVenda> litarItemDaVendaPorData(String dt);

    ItensDaVenda save(ItensDaVenda itensDaVenda);
}
