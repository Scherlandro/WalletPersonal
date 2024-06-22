package com.walletapi.serviceImpl;

import com.walletapi.dtos.ItensDaVendaDto;
import com.walletapi.model.ItensDaVenda;
import com.walletapi.repository.ItensDaVendaRepository;
import com.walletapi.service.ItensDaVendaService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ItenDaVendaServiceImpl implements ItensDaVendaService {


   final ItensDaVendaRepository itensDaVendaRepository;

    public ItenDaVendaServiceImpl(ItensDaVendaRepository repository) {

        this.itensDaVendaRepository = repository;
    }


    @Transactional
    public ItensDaVenda save(ItensDaVenda itensDaVenda) {
        return itensDaVendaRepository.save(itensDaVenda);
    }

    @Override
    public List<ItensDaVenda> findAll() {
        return itensDaVendaRepository.findAll();
    }

    @Override
    public Optional<ItensDaVenda> findById(Integer id) {
        return itensDaVendaRepository.findById(id);
    }

    @Override
    public List<ItensDaVendaDto> ConsultarItensVdEntreDatas(String dtIni, String dtFinal) {
        return itensDaVendaRepository.litarItemDaVendaEntreData(dtIni, dtFinal);
    }

    @Override
    public Optional<ItensDaVenda> litarItemDaVendaPorData(String dt) {
        return itensDaVendaRepository.litarItemDaVendaPorData(dt);
    }

 @Override
    public List<ItensDaVendaDto> litarItemDaVendaPorCliente(String nome) {
        return itensDaVendaRepository.litarItemDaVendaPorCliente(nome);
    }

}
