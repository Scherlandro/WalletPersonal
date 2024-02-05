package com.walletapi.repository;

import com.walletapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query(value = "Select p from Produto p" +
            " where trim(p.nomeProduto) like ?1%")
    List<Produto>listarProdutoPorNome(@Param("nomeProduto")String nomeProduto);

}
