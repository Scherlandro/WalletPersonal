package com.walletapi.repository;

import com.walletapi.dtos.ItensDaVendaDto;
import com.walletapi.model.ItensDaVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItensDaVendaRepository extends JpaRepository<ItensDaVenda, Integer> {

    @Query(value = " Select new com.walletapi.dtos.ItensDaVendaDto(i.id_itens_vd,i.codevendas,i.cod_produtos," +
            " i.descricao, i.valor_compra, i.valor_venda, i.qtd_vendidas, i.valor_parcial, v.dt_venda )" +
            "from Vendas v inner join ItensDaVenda i " +
            "on v.codevenda =  i.codevendas and v.dt_venda like %?1% ")
    List<ItensDaVendaDto> litarItemDaVendaPorData(@Param("dt_venda") String dt_venda);


    @Query(value = " Select new com.walletapi.dtos.ItensDaVendaDto(i.id_itens_vd,i.codevendas,i.cod_produtos," +
            " i.descricao, i.valor_compra, i.valor_venda, i.qtd_vendidas, i.valor_parcial, v.dt_venda )" +
            "from Vendas v inner join ItensDaVenda i " +
            "on v.codevenda = i.codevendas and " +
            "STR_TO_DATE(v.dt_venda,'%d/%m/%y')  BETWEEN STR_TO_DATE(:dtIni,'%d/%m/%y') AND STR_TO_DATE(:dtFinal,'%d/%m/%y') ")
    List<ItensDaVendaDto> litarItemDaVendaEntreData(@Param("dtIni") String dtIni, @Param("dtFinal") String dtFinal);


    @Query(value = " Select new com.walletapi.dtos.ItensDaVendaDto(i.id_itens_vd,i.codevendas,i.cod_produtos," +
            " i.descricao, i.valor_compra, i.valor_venda, i.qtd_vendidas, i.valor_parcial, v.dt_venda )" +
            "from Vendas v inner join ItensDaVenda i " +
            "on v.codevenda = i.codevendas and v.nomeCliente = ?1")
    List<ItensDaVendaDto> litarItemDaVendaPorCliente(@Param("nomeCliente") String nomeCliente);




}

