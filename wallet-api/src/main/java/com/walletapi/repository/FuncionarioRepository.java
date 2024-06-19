package com.walletapi.repository;

import com.walletapi.model.Funcionario;
import com.walletapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Integer> {

    @Query("SELECT f FROM Funcionario f WHERE trim(f.nome_funcionario)  like ?1%")
    List<Funcionario> findByFirstName(@Param("nome_funcionario") String firstName);

    @Query("SELECT e FROM Funcionario e WHERE lower(e.nome_funcionario) LIKE lower(concat('%',:nome_funcionario,'%')) " +
                    "AND e.salario >= :salario ORDER BY e.salario DESC" )
    List<Funcionario> findByNameLikeAndSalaryAbove(@Param("nome_funcionario") String nome,
                                                   @Param("salario") BigDecimal salario);

}
