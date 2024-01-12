package com.walletapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_funcionario;

    @Column(nullable = false, length = 50)
    private String nome_funcionario;

    @Column(nullable = false, length = 15)
    private String rg;

    @Column(nullable = false, length = 15)
    private String cpf;

    @Column(nullable = false, length = 10)
    private String dt_nascimnento;

    @Column(nullable = false, length = 10)
    private String dt_admissao;

    @Column(nullable = false, length = 10)
    private String dt_demisao;

    @Column(nullable = false, length = 20)
    private String cep = new Endereco().getCep();

    @Column(nullable = false, length = 30)
    private String telefone;

    @Column(nullable = false, length = 30)
    private String celular;

    @Column(nullable = false, length = 30)
    private String zap;


}
