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

 /*   @Column(name = "codevenda", length = 20)
    private String codevenda;
*/
    @Column(length = 11)
    private Integer id_cliente;

    @Column(name = "nome_cliente",length = 45)
    private String nomeCliente;

    @Column(length = 11)
    private Integer id_funcionario;

    @Column(length = 45)
    private String nome_funcionario;

    @Column(length = 10)
    private String dt_venda;

    private Double subtotal;

    private Double desconto;

    private Double totalgeral;

    @Column(length = 25)
    private String forma_de_pagamento;

    @Column(length = 3)
    private Integer numero_de_parcelas;

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

