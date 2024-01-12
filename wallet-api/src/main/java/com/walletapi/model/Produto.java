package com.walletapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto implements Serializable {
    private static final long serialversionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_produto;

    @Column(length = 20)
    private String cod_produto;

    @Column(length = 20, name = "coddofabricante")
    private String codDoFabricante;

    @JsonFormat(pattern = "dd/MM/yyyy")
   @Column(length = 10, name = "inicio_cadastro")
    private String dt_cadastro;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(length = 10, name = "ultima_atualizacao")
    private String ultima_atualizacao;

    @Column( length = 60)
    private String nome_produto;

    @Column(length = 45,name = "cordoproduto")
    private String corDoProduto;

    @Column(length = 4, name = "unidademedidas")
    private String unidadeMedidas;

    @Column(length = 300)
    private String obs;

    @Column( length = 30)
    private Double valor_compra;

    @Column( length = 20)
    private Double percentual;

    @Column( length = 30)
    private Double valor_venda;

    @Column( length = 11)
    private Integer quantidade_estoque;

    @Column( length = 11)
    private Integer estoque_minimo;

    @Column(length = 11, name = "qtd_vendidas")
    private Integer qtd_vendidas;

   @Column(length = 11)
    private Integer id_fornecedor;

    @Column(length = 11)
    private Integer id_marca;

    @Column(length = 11)
    private Integer id_modelo;

    @Lob
    @Column(length = 100000)
    private byte[] foto_produto;
}
