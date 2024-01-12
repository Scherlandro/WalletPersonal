package com.walletapi.repository;

import com.walletapi.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Integer> {
/*
    List<Funcionario> findByFirstName(String firstName);
    @Query(
            "SELECT e FROM Funcionario e " +
                    "WHERE lower(e.nome_funcionario) LIKE lower(concat('%', :name, '%')) " +
                    "AND e.salario > :salario " +
                    "ORDER BY e.salario DESC"
    )
    List<Funcionario> findByNameLikeAndSalaryAbove(
            @Param("nome_funcionario") String name, @Param("salario") BigDecimal salario);
*/
}
