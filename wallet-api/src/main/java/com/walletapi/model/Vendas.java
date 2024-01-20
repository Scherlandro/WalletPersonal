package com.walletapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "vendas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venda")
    private Integer idVenda;

    @Column(name = "id_cliente", length = 11)
    private Integer idCliente;

    @Column(name = "nome_cliente", length = 45)
    private String nomeCliente;

    @Column(name = "id_funcionario", length = 11)
    private Integer idFuncionario;

    @Column(name = "nome_funcionario", length = 45)
    private String nomeFuncionario;

    @Column(name = "dt_venda", length = 10)
    private String dtVenda;

    private Double subtotal;

    private Double desconto;

    private Double totalgeral;

    @Column(name = "forma_de_pagamento", length = 25)
    private String formasDePagamento;

    @Column(name = "numero_de_parcelas", length = 3)
    private Integer qtdDeParcelas;

    @OneToMany
    @JoinColumn(name = "codevendas")
    private Collection<ItensDaVenda> itensVd;


    /*

   @Transient
   @OneToMany(mappedBy = "vendas", cascade = CascadeType.ALL, orphanRemoval = true)
      @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
      @JoinTable(name = "vendas", joinColumns = {
      @JoinColumn(name = "fk_Vd_itensVd", referencedColumnName = "cod_venda")},
     inverseJoinColumns = { @JoinColumn(name = "cod_vendas", referencedColumnName = "codevendas")})
      private final List<ItensDaVenda> itensDaVendaList;
    }*/
}

