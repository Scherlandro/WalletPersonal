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

    @Query("Select i.descricao, i.cod_produtos, i.qtd_vendidas," +
            " i.valor_venda, i.valor_parcial, v.dt_venda " +
            "from Vendas v inner join ItensDaVenda i " +
            "on i.codevendas =  i.codevendas and (v.dt_venda) like %?1% ")
    Optional<ItensDaVenda> litarItemDaVendaPorData(String dt_venda);


    @Query(value = " Select new com.walletapi.dtos.ItensDaVendaDto(i.id_itens_vd,i.codevendas,i.cod_produtos," +
            " i.descricao, i.valor_compra, i.valor_venda, i.qtd_vendidas, i.valor_parcial, v.dt_venda )" +
            "from Vendas v inner join ItensDaVenda i " +
            "on v.codevenda = i.codevendas and " +
            "STR_TO_DATE(v.dt_venda,'%d/%m/%y')  BETWEEN STR_TO_DATE(:dtIni,'%d/%m/%y') AND STR_TO_DATE(:dtFinal,'%d/%m/%y') ")
    List<ItensDaVendaDto> litarItemDaVendaEntreData(@Param("dtIni") String dtIni, @Param("dtFinal") String dtFinal);


    @Query("Select i.id_itens_vd,i.codevendas,i.cod_produtos," +
            " i.descricao, i.valor_venda, i.qtd_vendidas, i.valor_parcial, v.dt_venda " +
            " from Vendas v inner join ItensDaVenda i " +
            " where v.dt_venda between :dtIni and :dtFinal ")
    List<ItensDaVenda> litarItemDaVendaEntreData2(@Param("dtIni") String dtIni, @Param("dtFinal") String dtFinal);


    @Query(value = " Select new com.walletapi.dtos.ItensDaVendaDto(i.id_itens_vd,i.codevendas,i.cod_produtos," +
            " i.descricao, i.valor_compra, i.valor_venda, i.qtd_vendidas, i.valor_parcial, v.dt_venda )" +
            "from Vendas v inner join ItensDaVenda i " +
            "on v.codevenda = i.codevendas and v.nomeCliente = ?1")
    List<ItensDaVendaDto> litarItemDaVendaPorCliente(@Param("nomeCliente") String nomeCliente);


/*

 @Query(value = "Select new com.biontecapi.dtos.ItensDaVendaDto(v.codevenda, i.cod_produtos, i.descricao," +
         " i.valor_venda, i.qtd_vendidas, i.valor_parcial, v.dt_venda) " +
         "from Vendas v inner join ItensDaVenda i " +
         "on v.codevenda = i.codevendas where i.codevendas = ?1 ")
 List<ItensDaVendaDto> litarItemDaVendaPorCod(@Param("codevendas")String codevendas);


 @Query("Select i.id_itens_vd,i.codevendas,i.cod_produtos," +
            " i.descricao, i.valor_venda, i.qtd_vendidas, i.valor_parcial, v.dt_venda " +
            " from Vendas v inner join ItensDaVenda i " +
            " where v.dt_venda between :dtIni and :dtFinal ")
    Collection<ItensDaVenda> litItensVdByDatesBetween(@Param("dtIni") Instant dtIni , @Param("dtFinal") Instant dtFinal);

  @Query("SELECT offer FROM OfferEntity offer " +
            "   JOIN offer.placeOwnership AS owner " +
            "   JOIN owner.place AS place " +
            "   WHERE place.id = :placeId  " +
            "   And to_char(offer.dayFrom, 'yyyy-MM-dd') = :offerDate AND " +

            <expression>,<operator>, GROUP, HAVING or ORDER expected got '('
            "   offer.repeating = false")
    List<OfferEntity> getAllForDate(@Param("placeId") Long placeId, @Param("offerDate") String offerDate);




    @Query("UPDATE ItensDaVenda i SET i.cod_produtos = :cod_produto")
    @Modifying
    void addPrefixToFirstName(@Param("cod_produto") String cod_produto);

 */


}

