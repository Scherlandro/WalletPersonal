package com.walletapi.repository;

import com.walletapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query(value = "Select p from Produto p where trim(p.nomeProduto) like ?1%")
    List<Produto>listarProdutoPorNome(@Param("nomeProduto")String nomeProduto);


}
