package com.walletapi.dtos;


import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendasDto {


    private Integer idVenda;
    private String codevenda;
    private Integer idCliente;
    @NotNull
    @NotBlank
    private String nomeCliente;
    private Integer idFuncionario;
    private String nomeFuncionario;
    private String dt_venda;
    private Double subtotal;
    private Double desconto;
    private Double totalgeral;
    private String formasDePagamento;
    @Size(max = 2)
    private Integer qtdDeParcelas;
    private Collection<ItensDaVendaDto> itensVd;


}
