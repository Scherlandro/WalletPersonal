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
    private Integer idCliente;
    @NotNull
    @NotBlank
    private String nomeCliente;
    private Integer idFuncionario;
    private String nomeFuncionario;
    private String dtVenda;
    private Double subtotal;
    private Double desconto;
    private Double totalgeral;
    private String formasDePagamento;
    @Size(max = 2)
    private Integer qtdDeParcelas;
    private Collection<ItensDaVenda> itensVd;


}
