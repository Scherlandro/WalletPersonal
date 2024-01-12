package com.walletapi.repository;

import com.walletapi.dtos.VendasDto;
import com.walletapi.model.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, Integer> {

  /*  @Query("Select new com.walletapi.dtos.VendasDto( v.idVenda, v.codevenda, " +
            " v.id_cliente, v.nome_cliente, v.id_funcionario, v.nome_funcionario, v.dt_venda," +
            " v.subtotal, v.desconto, v.totalgeral, v.forma_de_pagamento, v.numero_de_parcelas )" +
            "from Vendas v where v.codevenda = ?1 ")
    Optional<VendasDto> litarVendaPorCod(@Param("codevenda") String codevenda);

   */

    /*
     Optional<VendasDto> findVendasByCodevenda(String codevenda);
    Optional<VendasDto> findVendasByIdVenda(Integer id);
    */

    @Query("Select new com.walletapi.dtos.VendasDto( v.idVenda, v.id_cliente, v.nomeCliente,  " +
            " v.id_funcionario, v.nome_funcionario, v.dt_venda," +
            " v.subtotal, v.desconto, v.totalgeral, v.forma_de_pagamento, v.numero_de_parcelas, v.itensVd)" +
            "from Vendas v where v.nomeCliente = ?1 ")
    Optional<VendasDto> findVendasByNomeCliente(@Param("nome_cliente") String nome_cliente);

   // Optional<VendasDto> findVendasByNomeCliente(String name);
}
