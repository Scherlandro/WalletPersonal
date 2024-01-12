package com.walletapi.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.walletapi.model.ItensDaVenda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendasDto {

    private Integer idVenda;
    private Integer id_cliente;
    @NotNull
    @NotBlank
    private String nomeCliente;
    private Integer id_funcionario;
    private String nome_funcionario;
    private String dt_venda;
    private Double subtotal;
    private Double desconto;
    private Double totalgeral;
    private String forma_de_pagamento;
    @Size(max = 2)
    private Integer numero_de_parcelas;
    private Collection<ItensDaVenda> itensVd;

}
